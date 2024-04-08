package com.vogue;

import com.vogue.user.domain.SignUpVO;
import com.vogue.user.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VogueApplicationTests {

	@Autowired
	UserMapper mapper;

	@Test
	@DisplayName("회원가입 시 중복된 이메일 체크")
	void duplicateEmail() {
		SignUpVO vo = new SignUpVO();
		vo.setEmail("rkdans113@naver.com");

		int result = mapper.duplicateEmail(vo);

		assertThat(result).isEqualTo(1);
	}

	@Test
	@DisplayName("회원가입 시 중복된 회원여부 체크")
	void duplicateUser() {
		SignUpVO vo = new SignUpVO();
		vo.setName("강문호");
		vo.setTel("010-8637-1685");

		int result = mapper.duplicateUser(vo);

		assertThat(result).isEqualTo(1);
	}
}
