package cn.rainingapple.controller;

import cn.rainingapple.service.PbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AnalysisController {

    @Autowired
    PbService pbService;

    @RequestMapping("/cate_wechat")
    @ResponseBody
    public Map<String, Double> cate_wechat(HttpSession session){
        return pbService.cate_wechat(session);
    }

    @RequestMapping("/cate_alipay")
    @ResponseBody
    public Map<String, Double> cate_alipay(HttpSession session){
        return pbService.cate_alipay(session);
    }

    @RequestMapping("/cate_edu")
    @ResponseBody
    public Map<String, Integer> cate_edu(HttpSession session){
        return pbService.cate_edu(session);
    }

    @RequestMapping("/prop_edu")
    @ResponseBody
    public Map<String, Double> prop_edu(HttpSession session){
        return pbService.prop_edu(session);
    }

    @RequestMapping("/prop_alipay")
    @ResponseBody
    public Map<String, Double> prop_alipay(HttpSession session){
        return pbService.prop_alipay(session);
    }

    @RequestMapping("/prop_wechat")
    @ResponseBody
    public Map<String, Double> prop_wechat(HttpSession session){
        return pbService.prop_wechat(session);
    }
}
