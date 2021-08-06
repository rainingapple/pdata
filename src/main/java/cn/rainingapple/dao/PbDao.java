package cn.rainingapple.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Repository
public class PbDao {
    //模拟数据库的数据
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> get_edu(HttpSession session){
        String sql = "select * from edu_data where username = ?";
        return jdbcTemplate.queryForList(sql,session.getAttribute("loginname"));
    }

    public List<Map<String, Object>> get_alipay(HttpSession session){
        String sql = "select * from alipay_data where username = ?";
        return jdbcTemplate.queryForList(sql,session.getAttribute("loginname"));
    }

    public List<Map<String, Object>> get_wechat(HttpSession session){
        String sql = "select * from wechat_data where username = ?";
        return jdbcTemplate.queryForList(sql,session.getAttribute("loginname"));
    }

    public String get_pwd(String username){
        String sql = "select password from account where username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class,username);
        }catch (Exception e){
            return "";
        }
    }
}
