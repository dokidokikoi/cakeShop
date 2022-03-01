import request from "@/request";

export function getUserInfo() {
    return request.get("admin/user/currentUser")
}

export function saveUserInfo(info) {
    return request.put("admin/user/currentUser", info)
}

export function editPwd(info) {
    return request.put('admin/user/currentUser/pwd', info)
}