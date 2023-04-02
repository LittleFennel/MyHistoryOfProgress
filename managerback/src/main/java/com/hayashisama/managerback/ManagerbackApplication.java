package com.hayashisama.managerback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class ManagerbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerbackApplication.class, args);
    }
}
