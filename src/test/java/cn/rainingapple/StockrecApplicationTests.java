package cn.rainingapple;

import cn.rainingapple.dao.PbDao;
import cn.rainingapple.service.PbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockrecApplicationTests {

    @Autowired
    PbDao pbDao;

    @Autowired
    PbService pbService;

    @Test
    void contextLoads() {
    }

}
