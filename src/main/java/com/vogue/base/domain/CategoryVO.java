package com.vogue.base.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class CategoryVO {

  private int seq;
  private String name;
  private int upperSeq;
  private String url;
  private int depth;
  private int sort;
  private String useYn;
  private Date createdAt;
  private List<CategoryPermissionVO> permission;
  private String midCategory;


  @Builder
  public CategoryVO(int seq, String name, int upperSeq, String url, int depth, int sort, String useYn, Date createdAt, List<CategoryPermissionVO> permission, String midCategory) {
    this.seq = seq;
    this.name = name;
    this.upperSeq = seq;
    this.url = url;
    this.depth = depth;
    this.sort = sort;
    this.useYn = useYn;
    this.createdAt = createdAt;
    this.permission = permission;
    this.midCategory = midCategory;
  }
}
