import request from '@/request'

export function getRole() {
    return request.get('admin/role/current')
}
