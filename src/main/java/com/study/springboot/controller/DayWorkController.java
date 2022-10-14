package com.study.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.springboot.entity.DayWork;
import com.study.springboot.service.DayWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class DayWorkController {
    @Autowired
    private DayWorkService dayWorkService;
    @Autowired
    JdbcTemplate jdbcTemplate ;
    @GetMapping("/dayWork/showDayWork/{empPosition}/{empId}/{pageNum}")
    public String showDayWork(
            @PathVariable("pageNum") Integer pageNum,
            Model model){
        String sql = "select * from t_daywork";
        List<DayWork> dayWorkList=jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DayWork.class));
        //开启分页功能
        PageHelper.startPage(pageNum, 5);
        PageInfo<DayWork> page = new PageInfo<>();
        if (dayWorkList != null && dayWorkList.size() > 0) {
            //获取分页相关数据
            page  = new PageInfo<>(dayWorkList, 5);
            // 3、返回查询结果
        }
        System.out.println("page = " + page);
        model.addAttribute("page",page);
        return "table/day_table";
    }
    @GetMapping("/dayWork/addtwork/{empId}/{empPosition}")
    public String addDayWork(Model model){
        return "table/day_layouts";
    }
    @RequestMapping("/dayWork/insertwork/{empId}/{empPosition}")
    public String insertDayWork(@PathVariable("empId") Integer empId,
                                @PathVariable("empPosition") String empPosition,
                                @RequestParam("dayData") String dayData,
                                @RequestParam("dayMiaoshu") String dayMiaoshu,
                                Model model
    ) throws ParseException {
        String sql = "insert into t_daywork values(null,?,?) ";
        int i= jdbcTemplate.update(sql,dayData,dayMiaoshu);
        return "redirect:/dayWork/showDayWork/"+empPosition+"/"+empId+"/1";
    }


    @GetMapping("/dayWork/showDayWorkDetail/{dayId}/{empPosition}")
    public String showDayWorkDetail(@PathVariable("dayId") Integer dayId,
                                    @PathVariable("empPosition") String empPosition,
                                    Model model
                                    ){
        String sql = "select * from t_daywork where day_id=?";
        DayWork dayWork = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(DayWork.class), dayId);
        model.addAttribute("dayWork",dayWork);
        return "table/day_detail";
    }

    @GetMapping("/dayWork/deleteDayWorkById/{dayId}/{empPosition}/{empId}/{pageNum}")
    public String deleteDayWorkById(
            @PathVariable("dayId") Integer dayId,
            @PathVariable("empPosition") String empPosition,
            @PathVariable("empId") Integer empId,
            @PathVariable("pageNum") Integer pageNum
    ){
        String sql = "delete from t_daywork where day_id = ?";
        int i = jdbcTemplate.update(sql,dayId);
        return "redirect:/dayWork/showDayWork/"+empPosition+"/"+empId+"/"+pageNum;
    }
    @GetMapping("/daywork/editDayWork/{dayId}")
    public String editDayWork(@PathVariable("dayId") Integer dayId,Model model){
        String sql = "select * from t_daywork where day_id=?";
        DayWork dayWork = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(DayWork.class), dayId);
        model.addAttribute("dayWork",dayWork);
        return "table/day_edit";
    }
    @PostMapping("/dayWork/updateWork/{empId}/{empPosition}/{dayId}")
    public String updateWork(@PathVariable("empId") Integer empId,
                             @PathVariable("empPosition") String empPosition,
                             @PathVariable("dayId") Integer dayId,
                             @RequestParam("dayData") String dayData,
                             @RequestParam("dayMiaoshu") String dayMiaoshu
    ) throws ParseException {
        String sql = "update t_daywork set day_data=?,day_miaoshu=? where day_id=?";
        int i= jdbcTemplate.update(sql,dayData,dayMiaoshu,dayId);
        return "redirect:/dayWork/showDayWork/"+empPosition+"/"+empId+"/1";
    }


}
