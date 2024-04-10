package com.vogue.user.domain;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
public enum UserStatus {

  CREATED(HttpStatus.CREATED, "회원가입이 완료되었습니다. 로그인 후 이용해주세요."),

  HAS_EMAIL_SUCCESS(HttpStatus.OK, "사용할 수 있는 이메일입니다. 현재 이메일을 사용하시겠습니까?"),

  CONFLICT_EMAIL(HttpStatus.CONFLICT, "가입된 이메일이 존재합니다."),

  CONFLICT_USER(HttpStatus.CONFLICT, "가입된 회원정보가 존재합니다."),

  UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "Google Captcha 검증에 실패했습니다. 다시 시도해주세요.");


  private final HttpStatus code;
  private final String message;

  UserStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }

  public HttpStatus getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
