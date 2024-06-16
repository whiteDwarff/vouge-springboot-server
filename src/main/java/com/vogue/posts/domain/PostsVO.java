package com.vogue.posts.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostsVO {
  private int seq;            // 게시글 고유값
  private int upperSeq;       // 상위 카테고리
  private int lowerSeq;       // 하위 카테고리
  private String upperNm;     // 상위 카테고리 이름
  private String lowerNm;     // 하위 카테고리 이름
  private String prepend;     // 말머리
  private String title;       // 제목
  private String content;     // 내용
  private int authorSeq;      // 작성자 PK
  private String author;      // 작성자
  private String publicYn;    // 공개여부
  private String commentYn;   // 댓글여부
  private int readCount;      // 읽은 수
  private int likeCount;      // 좋아요 수
  private Date createdAt;     // 작성일
  private Date updatedAt;     // 수정일
  // 댓글 구현 시 Object -> CommentVO로 변경
  private List<HashMap<String, Object>> comments; // 댓글
  private int commentCount;


  // 게시글 상세
  @Builder(builderClassName = "DefaultBuilder", builderMethodName = "defaultBuilder")
  public PostsVO(int seq,
                 String upperNm,
                 String lowerNm,
                 String prepend,
                 String title,
                 String content,
                 int authorSeq,
                 String author,
                 String publicYn,
                 String commentYn,
                 int readCount,
                 int likeCount,
                 Date createdAt,
                 Date updatedAt,
                 List<HashMap<String, Object>> comments,
                 int commentCount) {
    this.seq = seq;
    this.lowerNm = lowerNm;
    this.upperNm = upperNm;
    this.prepend = prepend;
    this.title = title;
    this.content = content;
    this.authorSeq = authorSeq;
    this.author = author;
    this.publicYn = publicYn;
    this.commentYn = commentYn;
    this.readCount = readCount;
    this.likeCount = likeCount;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.comments = comments;
    this.commentCount = commentCount;
  }
  // 게시글 리스트
  @Builder(builderClassName = "PostListVOBuilder", builderMethodName = "PostListVO")
  public PostsVO(int seq,
                 String title,
                 String author,
                 Date createdAt,
                 int readCount,
                 int likeCount,
                 int commentCount) {
    this.seq       = seq;
    this.title     = title;
    this.author    = author;
    this.createdAt = createdAt;
    this.readCount = readCount;
    this.likeCount = likeCount;
    this.commentCount  = commentCount;
  }


}
