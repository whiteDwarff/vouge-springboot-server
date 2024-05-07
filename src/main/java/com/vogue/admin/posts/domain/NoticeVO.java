package com.vogue.admin.posts.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class NoticeVO {
  private int seq;
  private int upperSeq;
  private int lowerSeq;
  private String title;
  private String content;
  private String useYn;
  private Date createdAt;
  private Date updatedAt;

  @Builder
  public NoticeVO(int seq,
                  int upperSeq,
                  int lowerSeq,
                  String title,
                  String content,
                  String useYn,
                  Date createdAt,
                  Date updatedAt) {
    this.seq = seq;
    this.upperSeq = upperSeq;
    this.lowerSeq = lowerSeq;
    this.title = title;
    this.content = content;
    this.useYn = useYn;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
