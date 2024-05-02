package com.vogue.base.domain;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryVO {

  private int seq;            // 시퀀스
  private String name;
  private int upperSeq;       // 상위타입
  private String url;
  private int depth;          // 깊이
  private int sort;           // 정렬순서
  private String useYn;       // 사용여부    (Y: 사용, N: 미사용)
  private String postYn;      // 게시글 여부 (Y: 사용자, N:관리자)
  private Date createdAt;
  private String midCategory;
  private ArrayList<CategoryVO> children;
  private List<CategoryPermissionVO> permission;


  @Builder
  public CategoryVO(int seq, String name, int upperSeq, String url, int depth, int sort, String useYn, String postYn, Date createdAt, List<CategoryPermissionVO> permission, ArrayList<CategoryVO> children, String midCategory) {
    this.seq = seq;
    this.name = name;
    this.upperSeq = seq;
    this.url = url;
    this.depth = depth;
    this.sort = sort;
    this.useYn = useYn;
    this.postYn = postYn;
    this.createdAt = createdAt;
    this.permission = permission;
    this.children = children;
    this.midCategory = midCategory;
  }
}
