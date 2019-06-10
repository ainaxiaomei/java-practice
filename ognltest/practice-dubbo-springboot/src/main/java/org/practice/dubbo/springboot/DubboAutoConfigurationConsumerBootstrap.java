package org.practice.dubbo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class DubboAutoConfigurationConsumerBootstrap {


    public static void main(String[] args) { 
        SpringApplication.run(DubboAutoConfigurationConsumerBootstrap.class);
    }

} 