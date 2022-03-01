import axios from 'axios'
import { getToken, removeToken } from '@/request/token'
import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

const http = axios.create({
    baseURL: 'http://localhost:9000/',
    timeout: 10000
})

http.interceptors.request.use(config => {
    NProgress.start()
    if (getToken()) {
        config.headers.Authorization = getToken()
    }
    
    return config
}, err => {
    NProgress.done()
    Promise.reject(err)
})

http.interceptors.response.use(
    response => {
        NProgress.done()
        const res = response.data;
        if (res.code !== 200) {
            console.log("oh shit!!!")
            if (res.code === 400) {
                Message.error(res.msg)
                if (res.msg == 'token已过期') removeToken()
                return Promise.reject('error1');
            }
            if (res.code === 403) {
                Message.error(res.msg)
                return Promise.reject('error2');
            }
            return Promise.reject('error3');
        } else {
            return response;
        }
    },
    err => {
        NProgress.done()
        Message.error("连接超时")
        return Promise.reject(err);
    }
)

export default http