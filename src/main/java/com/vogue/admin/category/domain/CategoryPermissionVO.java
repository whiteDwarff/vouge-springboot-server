package com.vogue.admin.category.domain;

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

}
