package com.lukai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lukai.mapper")
public class DlykServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlykServerApplication.class, args);
    }

}
