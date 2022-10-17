package com.study.springboot.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.springboot.entity.Emp;
import com.study.springboot.entity.Memorials;
import com.study.springboot.service.EmpService;
import com.study.springboot.service.MemorialsService;
import com.study.springboot.util.UpLoadImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WorkController {
    @Autowired
    private MemorialsService memorialsService;
    @Autowired
    private EmpService empService;
    @RequestMapping (value="/work/showMemorialsDigestList/{empPosition}/{empId}/{pageNum}")
    protected String showMemorialsDigestList(
            @PathVariable("empPosition") String  empPosition,
            @PathVariable("empId") String  empId,
            @PathVariable("pageNum") Integer pageNum,
            Model model
    ) {
        // 1、调用 Service 方法查询数据
        //开启分页功能
        PageHelper.startPage(pageNum, 5);
        PageInfo<Memorials> page = new PageInfo<>();
        List<Memorials> memorialsList ;
        if(empPosition.equals("minister")){
            memorialsList = memorialsService.getAllMemorialsDigestByMinister(empId,pageNum);
        }else{
            memorialsList = memorialsService.getAllMemorialsDigest(pageNum);
        }
        List<Emp> empList = empService.getAllEmp();
        page  = new PageInfo<>(memorialsList, 5);
        //System.out.println("page = " + page);
        model.addAttribute("page",page);
        model.addAttribute("empList",empList);
        // 3、渲染视图
        return "table/dynamic_table";
    }
    @RequestMapping (value="/work/showMemorialsDigestListBySerach/{empPosition}/{empId}/{pageNum}/{action}")
    protected String showMemorialsDigestListBySerach(
            @PathVariable("empPosition") String  empPosition,
            @PathVariable("empId") String  empId,
            @PathVariable("pageNum") Integer pageNum,
            @RequestParam(name="memorialsTitle",defaultValue = "") String memorialsTitle,
            @RequestParam(name="memorialsName",defaultValue = "") String eId,
            @RequestParam(name="memorialsStatus",defaultValue = "") String mStatus,
            Model model
    ){
        // 1、调用 Service 方法查询数据
        //开启分页功能
        //String[] mStatus = new String[]{Status};
        String[] s =  mStatus.split(",");
        for (String i : s) {
            System.out.println("i = " + i);
        }
        //System.out.println("mStatus = " + s);
        PageHelper.startPage(pageNum, 5);
        PageInfo<Memorials> page = new PageInfo<>();
        List<Memorials> memorialsList ;
        if(empPosition.equals("minister")){
            memorialsList = memorialsService.getAllMemorialsDetailBySearch(memorialsTitle,"",s,empId);
        }else{
            memorialsList = memorialsService.getAllMemorialsDetailBySearch(memorialsTitle,eId,s,"");
        }
        //System.out.println("memorialsList = " + memorialsList);
        if (memorialsList != null) {
            page  = new PageInfo<>(memorialsList, 5);
        }
        List<Emp> empList = empService.getAllEmp();
        model.addAttribute("page",page);
        model.addAttribute("empList",empList);
        // 3、渲染视图
        return "table/dynamic_table";
    }
    @ResponseBody
    @RequestMapping (value="/work/deleteCheckedMemorials/",method = RequestMethod.POST)
    protected void deleteCheckedMemorials(
            @RequestParam("params") Map<String,Object> map,
            Model model
    ){
        System.out.println("cs = " + map.get("ID"));

    }
    @RequestMapping(value="/work/showMemorialsDetail/{memorialsId}/{empPosition}",method= RequestMethod.GET)
    protected String showMemorialsDetail(
            @PathVariable("memorialsId") Integer memorialsId,
            @PathVariable("empPosition") String empPosition,
            Model model) {

        // 2、根据 memorialsId 从 Service 中查询 Memorials 对象
        Memorials memorials = memorialsService.getMemorialsDetailById(memorialsId);

        // **********************补充功能**********************
        // 获取当前奏折对象的状态
        Integer memorialsStatus = memorials.getMemorialsStatus();

        // 判断奏折状态
        if (memorialsStatus == 0 && !empPosition.equals("minister")) {
            // 更新奏折状态：数据库修改
            memorialsService.updateMemorialsStatus(memorialsId);
            // 更新奏折状态：当前对象修改
            //memorials.setMemorialsStatus(1);
        }
        // **********************补充功能**********************
        // 3、将 Memorials 对象存入请求域
        model.addAttribute("memorials", memorials);
        // 4、解析渲染视图
        return "table/memorials_detail";
    }
    @RequestMapping(value="/work/insertwork/{empId}/{empPosition}",method= RequestMethod.GET)
    protected String insertwork(){
        // 4、解析渲染视图
        return "table/form_layouts";

    }
    @RequestMapping(value = "/work/addtwork/{empID}/{empPosition}",method = RequestMethod.POST)
    protected String addtwork(
            @PathVariable("empID") Integer empID,
            @PathVariable("empPosition")String empPosition,
            @RequestParam("memorialsTitle")String memorialsTitle,
            @RequestParam("memorialsContentDigest")String memorialsContentDigest,
            @RequestPart("headerImg") MultipartFile headerImg)
            throws IOException {
        UpLoadImg upLoadImg = new UpLoadImg();
        String imageName = upLoadImg.upload(headerImg);
        
        // 1、从请求参数读取 memorialsId
        String qizouTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 执行更新
        Integer i = memorialsService.insertMemorials(empID,memorialsTitle,memorialsContentDigest,qizouTime,imageName);
        // 重定向回显示奏折列表页面
        return "redirect:/work/showMemorialsDigestList/"+empPosition+"/"+empID+"/1";


    }
    @RequestMapping(value = "/work/feedBack/{empID}",method = RequestMethod.POST)
    protected String feedBack(
            @RequestParam("memorialsId") Integer memorialsId,
            @RequestParam("empPosition") String empPosition,
            @RequestParam("feedbackContent") String feedbackContent,
            @PathVariable("empID") String empID
    ) {
        // 执行更新
        String feedTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        memorialsService.updateMemorialsFeedBack(memorialsId, feedbackContent,feedTime);

        // 重定向回显示奏折列表页面
        return "redirect:/work/showMemorialsDigestList/"+empPosition+"/"+empID+"/1";
    }
    @RequestMapping("/work/deleteMemorialById/{memorialsId}/{empPosition}/{empID}/{pagNo}")
    public String deleteMemorialById(
            @PathVariable("memorialsId") Integer memorialsId,
            @PathVariable("empPosition") String empPosition,
            @PathVariable("empID") String empID,
            @PathVariable("pagNo") Integer pagNo
        ){

        memorialsService.deleteMemorial(memorialsId);

        return "redirect:/work/showMemorialsDigestList/"+empPosition+"/"+empID+"/"+pagNo;

    }


}
