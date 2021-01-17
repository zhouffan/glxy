package com.atguigu.eduservice.entity.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: 进击的烧年.
 * @Date: 2021-01-17
 * @Description:
 *
 * Excel对应的实体类
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}