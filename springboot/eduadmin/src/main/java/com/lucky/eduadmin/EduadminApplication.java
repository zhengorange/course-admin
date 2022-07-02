package com.lucky.eduadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lucky.eduadmin.mapper")
public class EduadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduadminApplication.class, args);
    }

}
