package org.practice.dubbo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DubboAutoConfigurationConsumerBootstrap {


    public static void main(String[] args) { 
        SpringApplication.run(DubboAutoConfigurationConsumerBootstrap.class);
    }

} 