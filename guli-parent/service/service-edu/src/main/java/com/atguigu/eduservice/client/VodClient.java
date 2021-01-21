package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Author: 进击的烧年.
 * @Date: 2021-01-21
 * @Description:
 *
 * 创建client包
 * @FeignClient注解用于指定从哪个服务中调用功能 ，名称与被调用的服务名保持一致。
 * @GetMapping注解用于对被调用的微服务进行地址映射。
 * @PathVariable注解一定要指定参数名称，否则出错
 * @Component注解防止，在其他位置注入CodClient时idea报错
 *
 */
@FeignClient("service-vod")
@Component
public interface VodClient {
    @DeleteMapping(value = "/admin/vod/video/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);
}