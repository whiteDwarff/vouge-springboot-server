package com.vogue.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ScheduleStatus {

  // 일정 등록, CREATED 201
  CREATED(HttpStatus.CREATED, "일정이 등록되었습니다."),

  // 일정 등록 실패,  INTERNAL_SERVER_ERROR 500
  INTERNAL_SERVER_ERROR_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "일정 등록에 실패하였습니다"),

  // 인증된 사용자가 아님 UNAUTHORIZED 401
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증된 사용자가 아닙니다."),

  // 필수 파라미터 (id)가 넘어오지 않음 BAD_REQUEST 400
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

  // 삭제에 실패, INTERNAL_SERVER_ERROR 500
  INTERNAL_SERVER_ERROR_DEL(HttpStatus.INTERNAL_SERVER_ERROR, "일정 삭제에 실패하였습니다."),

  // 삭제에 성공 OK 200
  OK(HttpStatus.OK, "일정이 삭제되었습니다.")
  ;


  private final HttpStatus code;
  private final String message;

  ScheduleStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }
}
