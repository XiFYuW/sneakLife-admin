package com.sneaklife.config.mvc;

import com.sneaklife.config.filter.FileUploadInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/3/9 11:20
 */
@Configuration
public class SneakLifeWebMvc implements WebMvcConfigurer {

    @Resource
    private FileUploadInterceptor fileUploadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        registry.addInterceptor(fileUploadInterceptor).addPathPatterns("/**");
    }
}
