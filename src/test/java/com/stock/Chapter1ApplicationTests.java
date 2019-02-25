package com.stock;

import com.stock.domain.UserInfo;
import com.stock.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter1ApplicationTests {

    @Autowired
    private LoginService loginService;

    @Test
    public void contextLoads() {
        UserInfo user = loginService.selectByKey("10000");
        System.out.println(user.getName());
    }

}
