import request from '@/request'

export function getComments(id) {
    return request.get(`comments/${id}`)
}

export function recover(info) {
    return request.post('admin/comments', info)
}

export function publish(info) {
    return request.post('admin/comments/publish', info)
}