package com.luoyifan.shardingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author EvanLuo
 * @date 2018/11/5 16:36
 */
@SpringBootApplication
@Configuration
public class Application {
    public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
    }

}
