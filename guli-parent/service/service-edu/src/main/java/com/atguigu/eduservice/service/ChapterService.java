package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Chapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-18
 */
public interface ChapterService extends IService<Chapter> {
    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    boolean removeByCourseId(String courseId);
}