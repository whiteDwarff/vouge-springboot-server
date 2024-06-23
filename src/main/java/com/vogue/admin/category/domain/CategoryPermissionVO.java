package com.vogue.admin.category.domain;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@Data
public class CategoryPermissionVO {
  private int seq;
  private String idntfCd;
  private String idntfNm;
  private int categorySeq;
  private String access;
  private String add;
  private String update;
  private String delete;
  Date created_at;

  @Builder
  public CategoryPermissionVO(int seq, String idntfCd, String idntfNm, int categorySeq,  String access, String add, String update, String delete) {
    this.seq = seq;
    this.idntfCd = idntfCd;
    this.idntfNm = idntfNm;
    this.categorySeq = categorySeq;
    this.access = access;
    this.add = add;
    this.update = update;
    this.delete = delete;
  }
}
