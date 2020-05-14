package com.kang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.kang.mapper")
public class BaseApplication {

	public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
