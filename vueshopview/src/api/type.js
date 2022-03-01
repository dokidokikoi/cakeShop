import request from "@/request";

export function getAllType() {
    return request.get('type')
}