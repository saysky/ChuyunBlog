package com.example.blog.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * 拦截器，资源路径配置
 */
@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.blog")
@PropertySource(value = "classpath:application.yaml", encoding = "UTF-8")
public class MvcConfig implements EnvironmentAware, WebMvcConfigurer  {

    private Environment env;
    @Override
    public void setEnvironment(Environment environment) {
        this.env=environment;
    }


    @Autowired
    private ThymeleafViewResolver viewResolver;


    /**
     * 配置静态资源路径
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/themes/")
                .addResourceLocations("classpath:/robots.txt");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + System.getProperties().getProperty("user.home") + "/sens/upload/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/images/favicon.ico");

        configureThymeleafStaticVars(viewResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }




    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = Maps.newHashMap();
            vars.put("staticCdnUrl", env.getProperty("application.staticCdnUrl"));
            vars.put("version", env.getProperty("application.version"));
            viewResolver.setStaticVariables(vars);
        }
    }
}
