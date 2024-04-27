package com.vogue.code;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CategoryStatus {
  // 카테고리 생성
  CREATED(HttpStatus.CREATED, "카테고리가 생성되었습니다."),

  CONFLICT_NAME(HttpStatus.CONFLICT, "등록된 카테고리 이름이 존재합니다."),

  UPDATE(HttpStatus.CREATED, "카테고리가 수정되었습니다"),

  INTERNAL_SERVER_ERROR_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 수정에 실패하였습니다"),

  DELETE(HttpStatus.OK, "카테고리가 삭제되었습니다."),

  INTERNAL_SERVER_ERROR_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 삭제에 실패하였습니다"),
  ;




  private final HttpStatus code;
  private final String message;

  CategoryStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }
}
