package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.GuliException;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.entity.pojo.VideoInfoForm;
import com.atguigu.eduservice.mapper.VideoMapper;
import com.atguigu.eduservice.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-18
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.save(video);
        if (!result) {
            throw new GuliException(20001, "课时信息保存失败");
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
//从video表中取数据
        Video video = this.getById(id);
        if (video == null) {
            throw new GuliException(20001, "数据不存在");
        }

        //创建videoInfoForm对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video, videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
//保存课时基本信息
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.updateById(video);
        if (!result) {
            throw new GuliException(20001, "课时信息保存失败");
        }
    }

    @Override
    public boolean removeVideoById(String id) {
        //删除视频资源 TODO
        //查询云端视频id
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }



        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        Integer count = baseMapper.delete(queryWrapper);
        return null != count && count > 0;
    }
}
