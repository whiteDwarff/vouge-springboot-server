package com.vogue.base.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PermissionVO {
  private String idntfCd;
  private String idntfNm;
  private String memo;
  private int sort;

  @Builder
  public PermissionVO(String idntfCd, String idntfNm, String memo, int sort) {
    this.idntfCd = idntfCd;
    this.idntfNm = idntfNm;
    this.memo = memo;
    this.sort = sort;
  }

}
