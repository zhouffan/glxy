package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.query.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-14
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 分页
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
