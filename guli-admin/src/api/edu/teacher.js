import request from '@/utils/request'
const api_name = '/admin/edu/teacher'
export default {
    getPageList(page, limit, searchObj) {
        debugger
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
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