package com.indent.multitenanttodoapplication.infrastructure.config;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.JwtUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfig {
    private final JwtUtil jwtUtil;
    public FilterConfig(JwtUtil jwtUtil) {
    this.jwtUtil=jwtUtil;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(){
        FilterRegistrationBean<JwtFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new JwtFilter(jwtUtil));
        reg.addUrlPatterns("/*");
        return reg;
    }
}
