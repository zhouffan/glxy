package com.atguigu.servicebase.config;

import com.atguigu.commonutils.ExceptionUtil;
import com.atguigu.commonutils.GuliException;
import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: 进击的烧年.
 * @Date: 2021-01-16
 * @Description:
 * 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error();
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        e.printStackTrace();
        int length = 10;
        log.trace("Spring boot启动初始化了 {} 个 Bean", length);
        log.debug("Spring boot启动初始化了 {} 个 Bean", length);
        log.info("Spring boot启动初始化了 {} 个 Bean", length);
        log.warn("Spring boot启动初始化了 {} 个 Bean", length);
        log.error("Spring boot启动初始化了 {} 个 Bean", length);
        log.error(ExceptionUtil.getMessage(e));
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}