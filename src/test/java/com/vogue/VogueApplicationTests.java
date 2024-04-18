package com.vogue;

import com.vogue.base.mapper.PermissionMapper;
import com.vogue.base.domain.PermissionVO;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.mapper.UserMapper;
import com.vogue.user.service.CaptchaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VogueApplicationTests {

	@Autowired
	UserMapper mapper;
	@Autowired
	CaptchaService service;

	@Autowired
	PermissionMapper iMapper;

	@Test
	void test() {
		List<PermissionVO> a = iMapper.getPermission();
	}

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

	@Test
	@DisplayName("토큰 테스트")
	void getToken() {
		String token = "03AFcWeA6BVNO_IVobEEtqFlU4LezosN33neYTPbtddFCfOIdhHW3jrVIANsmo_HqmEfBuTkPlQDde_TyYmvVwLthBEFWdSSVfdZ30QrHJiE0yVbpvckMYnT9Kqhp2SNSivdjLdQHtsy5cbONNGw7Ey3cEWtMyR9Th91d3BJRlCmSpSbebgld0-Be3QXWLNxB7wCzYedZhWhTaJRHlJTTiI93z5KUi_W9E_R-TTwrhUdqLYksWV0JmtqDUgpPZFXBGPCWoj-kTx-ukWQyhZ8aEkvDkSG022ASPV8uNdPNtkS5VN4ltZPYQfOlxiaiSuPox4KXaAepS9Px8X0KAtS9PRubLwXfOydYDk48jseWpLMpU7epDq4_Xnz-EHmTtq6DFejexHhjb_mnmtbYD4u3cpcXnx--0uFft9fsWUXmkP3XY-ey8iFI5TTvr4tFS-0NcVW_KjD1mDXpRR0XOEx_g0qtcH3oWaEvJ5WfBncdEU0J7OV1r0wog_-yr9S91vBg5jDxMK5yaBeFVm-tmzojDqTZLCzfvoEPxBfgeUOMXOY9pRLsExJweQvpG5j-lCUMJnRLKyoPGkzzt2NQb_tgCxASyNABU4JF2_JwOsizVPVukSo5r2hIsI0DeDxuR9p3-Tc-DusCHcK7JN0Yd2qS66K_mjosFg-ATWmma49iXz4JKHsguixfCvZBlHAkY3UoPABdL3sBkVLtdgQzK_k4HHAcSZcF97xO-gHleFjqf2Ncb18eDwu30BDedgkNYAVgzyu3UeJmodoGYHQie0hf-1lTKC2gd2mG6Wnaephxqs-kDxXijxtpgciynXVhot49LbYQgxRIJI34y_hk0oM-2_0NPPYE-HLCJ_rr6RCWfYofIV4wWzSq8VYWZAK8BbhT1rqJPKulmYuq3p1_AWopHV3K3OWHjRM_4VnHFKO323Ajln5Jh718dQm00Js57JHgGPLo5xYfcWqxvKoGATKe_vO5pRVlHWs8xTkAKNZmYJ8_FJwwy2lNItDmxXndpE7hA3kie7tPrLuETL2AGyPrfplJ0yVfHpqkCuOuO8GVgMvQuqZqLn_SignrO2hO8Jctcd1BJCZ4wCSjGOG050ZDotwDSyROVS0sZCVov6BXLP7QjN2MqNPH-QAEmwW4yplAQhXCfR81gdHVC7__NT5nzDr6k8UvoQTr0FHOQRjmgxgNCkF2IcvAKHMbxlBv2f3bGx0TquJZLPucKi5AWZJGuhN1ympO3bKIj9EhQksSeIA9jAz9g_FYJagsI18RuBdp6SToiGH0RUvQyoXQwTaEGGZ0NocEyrgKjc-v_1ArQwvUmW1Ld4ffCxI5suYLgBS6-UuZ7Drae5gWxO6F_lGJmc0V9VUMPSe2Eu-hzivlZRCPabAdrXDQGo_Iubz96J8bPdSUFit5q2xfyme0fZr5EWDR9pi251oBECFk7tNjoritwBTsp9rINiAhXGRI2h4ov_jWI6fxrK_BoNGny4NkzslwtgyjjlZNbEi0Rb72obI4wvs-vaP2LFWvEGuuIMqKiLWgyfjIj7Fq-aJN8v0XHbQ0ElOeT0Dd5Bi4Vzg1i6SOkZzVHlLR1YVvuL388P7zTOBKCltaB7eUwvbVaINQ8qwTcBxEcOy7lvI-pe3vWG6SbbRPOIxjbyC2iVvBtDmIzV24o6gXXV1fwuY_VQpJ";
		Boolean a = service.verifyToken(token);
		assertThat(a).isEqualTo(true);
	}
}
