package com.vogue.base.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class AuthPermissionVO {

  private String idntfCd;
  private String idntfNm;
  private String memo;
  private int sort;

}
