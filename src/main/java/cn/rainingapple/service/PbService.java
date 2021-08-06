package cn.rainingapple.service;

import cn.rainingapple.dao.PbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PbService {
    @Autowired
    PbDao pbDao;

    public List<Map<String, Object>> get_edu(HttpSession session){
        return pbDao.get_edu(session);
    }

    public List<Map<String, Object>> get_alipay(HttpSession session){
        return pbDao.get_alipay(session);
    }

    public List<Map<String, Object>> get_wechat(HttpSession session){
        return pbDao.get_wechat(session);
    }

    public String get_pwd(String username){
        return pbDao.get_pwd(username);
    }

    public HashMap<String, Double> cate_alipay(HttpSession session){
        List<Map<String, Object>> pbDao_alipay = pbDao.get_alipay(session);
        HashMap<String,Double> hashMap = new HashMap<>();
        for(Map<String, Object> elem : pbDao_alipay){
            String cate_name = (String) elem.get("交易分类");
            Double amount = (Double) elem.get("金额");
            if(!hashMap.containsKey(cate_name)){
                hashMap.put(cate_name,amount);
            }else{
                hashMap.put(cate_name,hashMap.get(cate_name)+amount);
            }
        }
        return hashMap;
    }

    public HashMap<String, Double> prop_alipay(HttpSession session){
        List<Map<String, Object>> pbDao_alipay = pbDao.get_alipay(session);
        HashMap<String,Double> hashMap = new HashMap<>();
        for(Map<String, Object> elem : pbDao_alipay){
            String cate_name = (String) elem.get("收/付款方式");
            Double amount = (Double) elem.get("金额");
            if(!hashMap.containsKey(cate_name)){
                hashMap.put(cate_name,amount);
            }else{
                hashMap.put(cate_name,hashMap.get(cate_name)+amount);
            }
        }
        hashMap.remove("                    ");
        return hashMap;
    }

    public Map<String, Double> cate_wechat(HttpSession session){
        List<Map<String, Object>> pbDao_wechat = pbDao.get_wechat(session);
        Map<String,Double> hashMap = new HashMap<>();
        for(Map<String, Object> elem : pbDao_wechat){
            String cate_name = (String) elem.get("交易类型");
            String amount_it = elem.get("金额(元)").toString();
            if(cate_name.indexOf('(')!=-1){
                cate_name = cate_name.substring(0,cate_name.indexOf("("));
            }
            Double amount = Double.parseDouble(amount_it.substring(1));
            if(!hashMap.containsKey(cate_name)){
                hashMap.put(cate_name,amount);
            }else {
                hashMap.put(cate_name,hashMap.get(cate_name)+amount);
            }
        }
        return hashMap;
    }

    public Map<String, Double> prop_wechat(HttpSession session){
        List<Map<String, Object>> pbDao_wechat = pbDao.get_wechat(session);
        Map<String,Double> hashMap = new HashMap<>();
        for(Map<String, Object> elem : pbDao_wechat){
            String cate_name = (String) elem.get("支付方式");
            String amount_it = elem.get("金额(元)").toString();
            if(cate_name.indexOf('(')!=-1){
                cate_name = cate_name.substring(0,cate_name.indexOf("("));
            }
            Double amount = Double.parseDouble(amount_it.substring(1));
            if(!hashMap.containsKey(cate_name)){
                hashMap.put(cate_name,amount);
            }else{
                hashMap.put(cate_name,hashMap.get(cate_name)+amount);
            }
        }
        hashMap.remove("/");
        return hashMap;
    }

    public Map<String, Integer> cate_edu(HttpSession session){
        List<Map<String, Object>> pbDao_edu = pbDao.get_edu(session);
        Map<String,Integer> hashMap = new HashMap<>();
        for(Map<String, Object> elem : pbDao_edu){
            String cate_name;
            if(elem.get("成绩").equals("优")){
                cate_name = "90-100";
            }
            else if(elem.get("成绩").equals("良")){
                cate_name = "80-90";
            }
            else if(elem.get("成绩").equals("中")){
                cate_name = "70-80";
            }
            else if(elem.get("成绩").equals("通过")){
                cate_name = "80-90";
            }
            else if(elem.get("成绩").equals("缓考")){
                cate_name = "缓考";
            }
            else{
                int amount = Integer.parseInt((String) elem.get("成绩"));
                if(amount<60){
                    cate_name = "不及格";
                }
                else if(amount<70){
                    cate_name = "60-70";
                }
                else if(amount<80){
                    cate_name = "70-80";
                }
                else if(amount<90){
                    cate_name = "80-90";
                }
                else{
                    cate_name = "90-100";
                }
            }
            if(!hashMap.containsKey(cate_name)){
                hashMap.put(cate_name,1);
            }
            else {
                hashMap.put(cate_name,hashMap.get(cate_name)+1);
            }
        }
        return hashMap;
    }

    public Map<String, Double> prop_edu(HttpSession session){
        List<Map<String, Object>> pbDao_edu = pbDao.get_edu(session);
        Map<String,Integer> hashMap = new HashMap<>();
        Map<String,Integer> hashMap2 = new HashMap<>();
        Map<String,Double> hashMap3 = new HashMap<>();
        for(Map<String, Object> elem : pbDao_edu) {
            String cate_name = (String) elem.get("课程性质");
            int score;
            if (elem.get("成绩").equals("优")) {
                score=95;
            } else if (elem.get("成绩").equals("良")) {
                score=85;
            } else if (elem.get("成绩").equals("中")) {
                score=75;
            } else if (elem.get("成绩").equals("通过")) {
                score=85;
            } else if (elem.get("成绩").equals("缓考")) {
                continue;
            } else {
                score = Integer.parseInt((String) elem.get("成绩"));
            }
            if(!hashMap.containsKey(cate_name)){
                hashMap.put(cate_name,score);
                hashMap2.put(cate_name,1);
            }
            else{
                hashMap.put(cate_name,hashMap.get(cate_name)+score);
                hashMap2.put(cate_name,hashMap2.get(cate_name)+1);
            }
        }
        for(String elem:hashMap.keySet()) {
            hashMap3.put(elem, (double) (hashMap.get(elem) / hashMap2.get(elem)));
        }
        return hashMap3;
    }
}
