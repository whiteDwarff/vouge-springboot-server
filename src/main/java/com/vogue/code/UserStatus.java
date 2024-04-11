package com.vogue.code;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
public enum UserStatus {

  CREATED(HttpStatus.CREATED, "회원가입이 완료되었습니다. 로그인 후 이용해주세요."),

  HAS_EMAIL_SUCCESS(HttpStatus.OK, "사용할 수 있는 이메일입니다. 현재 이메일을 사용하시겠습니까?"),

  CONFLICT_EMAIL(HttpStatus.CONFLICT, "가입된 이메일이 존재합니다."),

  CONFLICT_USER(HttpStatus.CONFLICT, "가입된 회원정보가 존재합니다."),

  UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "Google Captcha 검증에 실패했습니다. 다시 시도해주세요."),

  OK(HttpStatus.OK, "로그인에 성공하였습니다."),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "가입된 정보가 존재하지 않습니다.");


  private final HttpStatus code;
  private final String message;

  UserStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }

  /*
  * @params : token
  * @Return : HttpStatus
  * message의 값을 비교하여 code 반환
  * */
  public static HttpStatus getStatusByMessage(String message) {
    for (UserStatus code : values()) {
      if (code.getMessage().equals(message)) {
        return code.getCode();
      }
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

}
