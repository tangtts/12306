package com.java.train.member.config;

import com.java.train.common.interceptor.LogInterceptor;
import com.java.train.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tsk
 * @date 2024/5/17 0017
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    LogInterceptor logInterceptor;

    @Resource
    MemberInterceptor memberInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/hello",
                        "/member/send-code",
                        "/member/login"
                );
    }
}
