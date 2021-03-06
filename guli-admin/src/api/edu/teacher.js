import request from '@/utils/request'
const api_name = '/admin/edu/teacher'
export default {
    getList() {
        return request({
            url: api_name,
            method: 'get'
        })
    },
    getPageList(page, limit, searchObj) {
        debugger
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'post',
            data: searchObj
        })
    },
    removeById(teacherId) {
        return request({
            url: `${api_name}/${teacherId}`,
            method: 'delete'
        })
    },
    save(teacher) {
        debugger
        return request({
            url: api_name,
            method: 'post',
            data: teacher
        })
    },
    getById(id) {
        return request({
            url: `${api_name}/${id}`,
            method: 'get'
        })
    },
    updateById(teacher) {
        debugger
        return request({
            url: `${api_name}/${teacher.id}`,
            method: 'put',
            data: teacher
        })
    }
}