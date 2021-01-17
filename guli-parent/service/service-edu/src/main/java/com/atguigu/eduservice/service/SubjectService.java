package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Subject;
import com.atguigu.eduservice.entity.pojo.SubjectNestedVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-17
 */
public interface SubjectService extends IService<Subject> {
    void batchImport(MultipartFile file);
    List<SubjectNestedVo> nestedList();
}
