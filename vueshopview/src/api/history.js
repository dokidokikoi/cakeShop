import request from "@/request"

export function getHistories() {
    return request.get('admin/history')
}