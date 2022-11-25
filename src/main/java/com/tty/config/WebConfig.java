package com.tty.config;

import com.tty.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 11:03
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoginIntercepter loginIntercepter(){
        return new LoginIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginIntercepter())
                .addPathPatterns("/mainpage", "/login");
    }
}
