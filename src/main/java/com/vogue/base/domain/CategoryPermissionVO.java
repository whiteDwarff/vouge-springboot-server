package com.vogue.base.domain;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@Data
public class CategoryPermissionVO {
  private int seq;
  private String idntfCd;
  private int categorySeq;
  private String access;
  private String add;
  private String update;
  private String delete;
  Date created_at;

  @Builder
  public CategoryPermissionVO(int seq, String idntfCd, int categorySeq, String access, String add, String update, String delete, Date created_at) {
    this.seq = seq;
    this.idntfCd = idntfCd;
    this.categorySeq = categorySeq;
    this.access = access;
    this.add = add;
    this.update = update;
    this.delete = delete;
    this.created_at = created_at;
  }
}
