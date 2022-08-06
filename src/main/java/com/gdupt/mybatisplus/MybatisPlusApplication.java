package com.gdupt.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//通过@MapperScan扫描指定包下的所有接口，为他们生成动态代理类，并交由IOC容器进行管理
//IOC容器是无法接管接口的，只有通过创建动态代理类才能实现对接口的管理
@MapperScan("com.gdupt.mybatisplus.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
