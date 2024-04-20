package com.vogue.base.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CategoryVO {

  private int seq;
  private String name;
  private int upperSeq;
  private int depth;
  private int sort;
  private String useYn;
  private Date createdAt;


  @Builder
  public CategoryVO(int seq, String name, int upperSeq, int depth, int sort, String useYn, Date createdAt) {
    this.seq = seq;
    this.name = name;
    this.upperSeq = seq;
    this.depth = depth;
    this.sort = sort;
    this.useYn = useYn;
    this.createdAt = createdAt;
  }
}
