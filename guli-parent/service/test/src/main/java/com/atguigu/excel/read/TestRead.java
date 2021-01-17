package com.atguigu.excel.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.atguigu.excel.DemoData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 进击的烧年.
 * @Date: 2021/1/17 18:34
 * @Description:
 */
public class TestRead {
    private static void write1(){
        // 写法1
        String fileName = "a.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ReadData.class, new
                ExcelListener()).sheet().doRead();

    }
    private static void write2() throws Exception{
        // 写法2：
        InputStream in = new BufferedInputStream(new
                FileInputStream("a.xlsx"));
        ExcelReader excelReader = EasyExcel.read(in, ReadData.class, new
                ExcelListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    public static void main(String[] args) throws Exception {
        write2();


    }

}
