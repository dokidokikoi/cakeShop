import request from '@/request'

export function saveOrder(id) {
    return request.post(`admin/orders/${id}`)
}

export function getOrderList() {
    return request.get('admin/orders/current')
}

export function pay(id) {
    return request.put(`admin/orders/current/${id}`)
}

export function deleteCurrentOrder(id) {
    return request.delete(`admin/orders/current/${id}`)
}

export function cancelCurrentOrder(id) {
    return request.put(`admin/orders/current/cancel/${id}`)
}

export function getAllOrderList(page) {
    return request.get('admin/orders', {params: page})
}

export function cancelOrder(id) {
    return request.put(`admin/orders/cancel/${id}`)
}

export function deleteOrder(id) {
    return request.delete(`admin/orders/${id}`)
}

export function resume(id) {
    return request.put(`admin/orders/resume/${id}`)
}