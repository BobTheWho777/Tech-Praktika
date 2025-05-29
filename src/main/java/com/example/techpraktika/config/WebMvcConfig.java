package com.example.techpraktika.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("constructionSite/list");
        registry.addViewController("/brigades").setViewName("brigades");
        registry.addViewController("/defects").setViewName("defects");
        registry.addViewController("/clients").setViewName("clients");
        registry.addViewController("/suppliers").setViewName("suppliers");
        registry.addViewController("/brigadeWorkers").setViewName("brigadeWorkers/list");
        registry.addViewController("/materials").setViewName("materials");
        registry.addViewController("/stages").setViewName("stages");
        registry.addViewController("/guide").setViewName("guide");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/about").setViewName("about");
    }
}