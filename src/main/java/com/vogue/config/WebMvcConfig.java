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
  @Value("${server.dir.notice}")
  private String noticeDir;
  @Value("${server.dir.template}")
  private String templateDir;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 사용자 업로드 게시판
    registry.addResourceHandler(postDir + File.separator + "**")
            .addResourceLocations("file://" +  filePath + postDir + File.separator);
    // 게시판 공지사항
    registry.addResourceHandler(noticeDir + File.separator + "**")
            .addResourceLocations("file://" +  filePath + noticeDir + File.separator);
    // 게시판 템플릿
    registry.addResourceHandler(templateDir + File.separator + "**")
            .addResourceLocations("file://" +  filePath + templateDir + File.separator);
  }

}