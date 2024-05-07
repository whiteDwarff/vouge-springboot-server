package com.vogue.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AdminPostsStatus {

  // 카테고리 생성
  NOTICE_CREATED(HttpStatus.CREATED, "공지사항이 등록되었습니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "괸라자에게 문의해주세요.")

  ;



  private final HttpStatus code;
  private final String message;

  AdminPostsStatus(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }
}
