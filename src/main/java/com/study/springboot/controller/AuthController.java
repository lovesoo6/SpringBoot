package com.study.springboot.controller;

import com.study.springboot.entity.Emp;
import com.study.springboot.service.EmpService;
import com.study.springboot.service.MemorialsService;
import com.study.springboot.util.ImperialCourtConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Slf4j
@Controller
public class AuthController {
    @Autowired
    private EmpService empService;
    @Autowired
    private MemorialsService memorialsService;
    /**
     * 来登录页
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }
    @PostMapping("/auth/login")
    public String doLogin(
            @RequestParam("loginAccount") String loginAccount,
            @RequestParam("loginPassword") String loginPassword,
            HttpSession session,
            RedirectAttributesModelMap model
    ) {

        // 1、尝试查询登录信息
        Emp emp = empService.getEmpByLogin(loginAccount, loginPassword);
        // 2、判断登录是否成功
        if (emp == null) {

            // 3、如果登录失败则回到登录页面显示提示消息
            //model.addAttribute("message", ImperialCourtConst.LOGIN_FAILED_MESSAGE);
            model.addFlashAttribute("message", ImperialCourtConst.LOGIN_FAILED_MESSAGE);
            return "redirect:/login";

        } else {

            int sumEmp = empService.getSumAllEmp();
            int sumMemoria = memorialsService.getSumMemorials();
            // 如果登录成功则将登录信息存入 Session 域
            session.setAttribute("sumEmp", sumEmp);
            session.setAttribute("sumMemoria", sumMemoria);
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);
            return "redirect:/main.html";
        }
    }
    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){

        log.info("当前方法是：{}","mainPage");
        return "main";

    }

    @GetMapping(value = "/auth/logout")
    protected String logout(HttpServletRequest request
    ) {

        // 1、通过 request 对象获取 HttpSession 对象
        HttpSession session = request.getSession();
        // 2、将 HttpSession 对象强制失效
        session.invalidate();
        return "redirect:/login";
    }
}
