package com.study.springboot.controller;


import com.alibaba.excel.EasyExcel;
import com.study.springboot.entity.DayWork;
import com.study.springboot.util.excel.ResponseExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class ExportExcelController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseExcel(name = "日志", sheet = "rizhi")
    @GetMapping("/dayWork/exportWork/{empId}/{empPosition}")
    public void exportWork(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DayWork.class).sheet("模板").doWrite(data());


    }


    private List<DayWork> data() {
        String sql ="select * from t_daywork";

        List<DayWork> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DayWork.class));
        System.out.println("list = " + list);

        return list;
    }
}