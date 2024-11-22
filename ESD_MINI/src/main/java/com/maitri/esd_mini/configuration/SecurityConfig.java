package com.maitri.esd_mini.configuration;

import com.maitri.esd_mini.helper.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("Inside Intreceptor=======================");
        // Apply the interceptor to all endpoints except /auth/login
        registry.addInterceptor(requestInterceptor)
//                .excludePathPatterns("/**");
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/login", "/api/v1/student/create_account");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
