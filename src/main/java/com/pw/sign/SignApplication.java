package com.pw.sign;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.pw.sign.mapper")
@Slf4j
public class SignApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SignApplication.class, args);
    }

}
