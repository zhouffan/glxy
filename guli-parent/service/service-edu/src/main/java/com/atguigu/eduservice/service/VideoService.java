package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.entity.pojo.VideoInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-18
 */
public interface VideoService extends IService<Video> {
    boolean getCountByChapterId(String chapterId);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

    boolean removeByCourseId(String courseId);
}
