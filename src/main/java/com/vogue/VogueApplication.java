package com.vogue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vogue.user.mapper")
@SpringBootApplication
public class VogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(VogueApplication.class, args);
	}


}
