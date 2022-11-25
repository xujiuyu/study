package com.tty;

import com.tty.entity.User;
import com.tty.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "com.tty.mapper")
@EnableAspectJAutoProxy
public class Bj2207ShortenUrlApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Bj2207ShortenUrlApplication.class, args);
    }

}
