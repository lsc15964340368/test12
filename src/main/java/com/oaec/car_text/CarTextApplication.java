package com.oaec.car_text;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.oaec.car_text.dao")
public class CarTextApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarTextApplication.class, args);
    }

}
