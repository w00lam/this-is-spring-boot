package com.example.demo.config;

import com.example.demo.filter.AccessKeyFilter;
import com.example.demo.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogFilter());
        bean.addUrlPatterns("/api/*");
        bean.setOrder(1);
        bean.setName("Log Filter");
        return bean;
    }

    @Bean
    public FilterRegistrationBean<AccessKeyFilter> accessKeyFilter() {
        FilterRegistrationBean<AccessKeyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AccessKeyFilter());
        bean.addUrlPatterns("/api/*");
        bean.setOrder(2);
        bean.setName("Access Key Filter");
        return bean;
    }
}
