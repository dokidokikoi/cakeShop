import request from "@/request";

export function getCarCounts(id) {
    return request.get(`admin/car/counts/${id}`)
}

export function getCar(id) {
    return request.get('admin/car/'+id)
}

export function saveCar(info) {
    return request.post('admin/car', info)
}

export function deleteCar(id) {
    return request.delete('admin/car/'+id)
}