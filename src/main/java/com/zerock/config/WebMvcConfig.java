package com.zerock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file:" + uploadPath);
    }
}
