package com.vogue.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ScheduleStatus {

  /*
  * 일정 등록, CREATED
  * */
  OK(HttpStatus.OK, "일정이 등록되었습니다."),
  /*
  * 일정 등록 실패,  INTERNAL_SERVER_ERROR
  * */
  INTERNAL_SERVER_ERROR_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "일정 등록에 실패하였습니다"),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증된 사용자가 아닙니다.")
  ;


  private final HttpStatus code;
  private final String message;

  ScheduleStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }
}
