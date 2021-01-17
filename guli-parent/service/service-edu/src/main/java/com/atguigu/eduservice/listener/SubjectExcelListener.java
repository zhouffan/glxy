package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.commonutils.GuliException;
import com.atguigu.eduservice.entity.Subject;
import com.atguigu.eduservice.entity.pojo.ExcelSubjectData;
import com.atguigu.eduservice.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    public SubjectService subjectService;

    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(ExcelSubjectData user, AnalysisContext analysisContext) {
        System.out.println("***" + user);
        if (user == null) {
            throw new GuliException(20001, "添加失败");
        }
        //添加一级分类
        Subject existOneSubject = this.existOneSubject(subjectService, user.getOneSubjectName());
        if (existOneSubject == null) {//没有相同的
            existOneSubject = new Subject();
            existOneSubject.setTitle(user.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }
        //获取一级分类id值
        String pid = existOneSubject.getId();
        //添加二级分类
        Subject existTwoSubject = this.existTwoSubject(subjectService, user.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new Subject();
            existTwoSubject.setTitle(user.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext
            context) {
        System.out.println("表头信息：" + headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    //判断一级分类是否重复
    private Subject existTwoSubject(SubjectService subjectService, String
            name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        Subject Subject = subjectService.getOne(wrapper);
        return Subject;
    }

    //判断一级分类是否重复
    private Subject existOneSubject(SubjectService subjectService, String
            name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        Subject Subject = subjectService.getOne(wrapper);
        return Subject;
    }
}