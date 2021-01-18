package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2021-01-18
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    /**
     *
     * 方式一：业务层组装多个表多次的查询结果
     * 方式二：数据访问层进行关联查询
     * 我们使用第二种方式实现
     *
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVoById(String id);

}
