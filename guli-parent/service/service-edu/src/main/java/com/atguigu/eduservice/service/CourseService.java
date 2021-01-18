package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.pojo.CourseInfoForm;
import com.atguigu.eduservice.entity.pojo.CourseQuery;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-18
 */
public interface CourseService extends IService<Course> {
    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);
}
