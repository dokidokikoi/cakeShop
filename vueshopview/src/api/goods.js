import request from "@/request";

export function getGoods(info) {
    return request.get('goods', {params: info})
}

export function getAllGoods(info) {
    return request.get('admin/goods', {params: info})
}

export function getGoodsByTypeId(id) {
    return request.get(`goods/category/${id}`)
}

export function getGoodsById(id) {
    return request.get(`goods/${id}`)
}

export function saveGoods(info) {
    return request.post('admin/goods', info)
}

export function editGoods(info) {
    return request.put('admin/goods', info)
}

export function deleteGoods(id) {
    return request.delete('admin/goods/'+id)
}

export function resumeGoods(id) {
    return request.put('admin/goods/resume/'+id)
}