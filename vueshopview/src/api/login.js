import request from "@/request"
import qs from 'qs'

export function login(info) {
  return request({
      url: 'http://localhost:9000/login',
      method: 'POST',
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      data: qs.stringify(info)
    }) 
}

export function logout() {
  return request.post('logout')
}


export function register(info) {
  return request.post('user/register', info)
}
