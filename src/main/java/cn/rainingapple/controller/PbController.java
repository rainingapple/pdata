package cn.rainingapple.controller;

import cn.rainingapple.service.PbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PbController {

    @Autowired
    PbService pbService;

    @RequestMapping("/")
    public String signin(){
        return "signin";
    }

    @RequestMapping("/index.html")
    public String index(){
        return "signin";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "signin";
    }

    @RequestMapping("/home")
    public String home(Model model,HttpSession session){
        model.addAttribute("edu_data",pbService.get_edu(session));
        return "homepage";
    }

    @RequestMapping("/edu_dashboard")
    public String edu(Model model,HttpSession session){
        model.addAttribute("edu_data",pbService.get_edu(session));
        return "edu_dashboard";
    }

    @RequestMapping("/alipay_dashboard")
    public String alipay(Model model,HttpSession session){
        model.addAttribute("alipay_data",pbService.get_alipay(session));
        return "alipay_dashboard";
    }

    @RequestMapping("/wechat_dashboard")
    public String wechat(Model model,HttpSession session){
        model.addAttribute("wechat_data",pbService.get_wechat(session));
        return "wechat_dashboard";
    }
}
