package com.atguigu.excel.read;

import com.alibaba.excel.annotation.ExcelProperty;

public class ReadData {
    //设置列对应的属性
    @ExcelProperty(index = 0)
    private String sid;
    //设置列对应的属性
    @ExcelProperty(index = 1)
    private String sname;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "ReadData{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                '}';
    }
}