package com.vogue.base.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseCode {

  // LOGIN STATUS -------------------------------------------------------------
  LOGIN_OK(HttpStatus.OK, "로그인에 성공하였습니다."),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "가입된 정보가 존재하지 않습니다."),

  UNAUTHORIZED_PWD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),

  UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "Google Captcha 검증에 실패했습니다. 다시 시도해주세요."),
  // LOGIN STATUS END ---------------------------------------------------------

  INSERT_OK(HttpStatus.OK, "등록 성공하였습니다."),

  UPDATE_OK(HttpStatus.OK, "수정 성공하였습니다."),




  OK(HttpStatus.OK, "에 성공하였습니다."),

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "에 실패하였습니다")

  ;


  private final HttpStatus status;
  private String message;

  /**
   * 기본 생성자
   * @params status, message
   * */
  BaseCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  /**
   * 등록 및 수정, 삭제 성공 시  status 및 message 반환
   * @params str (등록, 수정, 삭제)
   * @return HttpStatus.OK
   * */
  public static BaseCode getOK(String str) {
    OK.message = str + OK.getMessage();
    return OK;
  }
  /**
   * 등록 및 수정, 삭제 실패 시  status 및 message 반환
   * @params str (등록, 수정, 삭제)
   * @return HttpStatus.INTERNAL_SERVER_ERROR
   * */
  public static BaseCode getERROR(String str) {
    INTERNAL_SERVER_ERROR.message = str + INTERNAL_SERVER_ERROR.getMessage();
    return INTERNAL_SERVER_ERROR;
  }


}
