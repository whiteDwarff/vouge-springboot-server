package com.vogue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vogue.user.mapper")
@MapperScan("com.vogue.base.mapper")
@MapperScan("com.vogue.admin.category.mapper")
@MapperScan("com.vogue.admin.posts.mapper")
@MapperScan("com.vogue.admin.schedule.mapper")
@SpringBootApplication
public class VogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(VogueApplication.class, args);
	}


}
