package com.vogue.code;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CategoryStatus {
  // 카테고리 생성
  CREATED(HttpStatus.CREATED, "카테고리가 생성되었습니다."),

  CONFLICT_NAME(HttpStatus.CONFLICT, "등록된 카테고리 이름이 존재합니다.");


  private final HttpStatus code;
  private final String message;

  CategoryStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }
}
