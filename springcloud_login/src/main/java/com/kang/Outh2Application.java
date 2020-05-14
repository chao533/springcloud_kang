package com.kang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import tk.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.kang.mapper.mybatis")
public class Outh2Application {

	public static void main(String[] args) {
        SpringApplication.run(Outh2Application.class, args);
    }
}
