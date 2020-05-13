package com.kang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.kang.mapper.mybatis")
public class Outh2Application {

	public static void main(String[] args) {
        SpringApplication.run(Outh2Application.class, args);
    }
}
