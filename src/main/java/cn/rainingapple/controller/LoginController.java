package cn.rainingapple.controller;

import cn.rainingapple.service.PbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    PbService pbService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String name, @RequestParam("pwd")String pwd, Model model, HttpSession session){
        String real_pwd = pbService.get_pwd(name);
        if(pwd.equals(real_pwd)){
            session.setAttribute("loginname",name);
            return "redirect:/edu_dashboard";
        }
        else {
            model.addAttribute("msg","账号或密码错误");
            return "signin";
        }
    }
}
