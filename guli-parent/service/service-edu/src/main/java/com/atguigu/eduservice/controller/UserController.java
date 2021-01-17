package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.GuliException;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ResultCode;
import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.query.TeacherQuery;
import com.atguigu.eduservice.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="讲师管理")
@RestController
@RequestMapping("/admin/edu/user")
public class UserController {

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R login(String username, String password){
        return R.ok().data("token", "admin");
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("info")
    public R info(){
        return R.ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2720094376,361289091&fm=26&gp=0.jpg")
                ;
    }

}

