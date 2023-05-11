package com.ssafy.goat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.ssafy.goat.login.LoginCheckInterceptor;
import com.ssafy.goat.login.LoginArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/article/list", "/assets/**", "/attraction/**","/login/**",
//                        "/hotPlace/list", "/register","/notion/**","/tripPlan/list", "/api/attraction/**",
//                        "/css/**", "/file/**", "/icon/**", "/img/**", "/js/**");
    }
}