package com.vogue.base.domain;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryVO {

  private int seq;            // 시퀀스
  private String name;        // 카테고리명
  private int upperSeq;       // 상위타입
  private String url;         // URL
  private int depth;          // 깊이
  private int sort;           // 정렬순서
  private String useYn;       // 사용여부    (Y: 사용, N: 미사용)
  private String postYn;      // 게시글 여부 (Y: 사용자, N:관리자)
  private Date createdAt;     // 생성일
  private String midCategory; // 하위 카테고리
  private List<CategoryPermissionVO> permission;  // 권한


  @Builder
  public CategoryVO(int seq, String name, int upperSeq, String url, int depth, int sort, String useYn, String postYn, Date createdAt, List<CategoryPermissionVO> permission, String midCategory) {
    this.seq = seq;
    this.name = name;
    this.upperSeq = upperSeq;
    this.upperSeq = seq;
    this.url = url;
    this.depth = depth;
    this.sort = sort;
    this.useYn = useYn;
    this.postYn = postYn;
    this.createdAt = createdAt;
    this.permission = permission;
    this.midCategory = midCategory;
  }
}
