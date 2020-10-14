package com.oaec.car_text;

import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarTextApplicationTests {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.queryUserByName("刘世超");
        System.out.println(user);
    }

}
