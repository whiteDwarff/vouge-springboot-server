package com.vogue.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Value("${server.file.path}")
  private String filePath;

  @Value("${server.dir.posts}")
  private String postDir;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(postDir + File.separator + "**")
            .addResourceLocations("file://" +  filePath + postDir + File.separator);
  }

}