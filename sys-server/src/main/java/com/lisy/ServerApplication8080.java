package com.lisy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lisy
 * @Date: 2021/4/1
 * @version: 1.0
 * @Description: "启动类"
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lisy.mapper")
public class ServerApplication8080 {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication8080.class, args);

    }
}
