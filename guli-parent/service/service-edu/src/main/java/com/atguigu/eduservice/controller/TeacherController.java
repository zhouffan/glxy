package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping
    public List<Teacher> list(){
        return teacherService.list(null);
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }

}

