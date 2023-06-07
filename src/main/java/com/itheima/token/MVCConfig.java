package com.itheima.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器设置
@Configuration
public class MVCConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
//                表示拦截所有请求
                .addPathPatterns("/api/user/**");
//                表示取消对特定路径的拦截
                //.excludePathPatterns("/api/auth/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    // 跨域支持
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }
}
