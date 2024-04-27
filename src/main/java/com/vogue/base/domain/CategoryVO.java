package com.vogue.base.domain;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryVO {

  private int seq;
  private String name;
  private int upperSeq;
  private String url;
  private int depth;
  private int sort;
  private String useYn;
  private String postYn;
  private Date createdAt;
  private List<CategoryPermissionVO> permission;
  private String midCategory;


  @Builder
  public CategoryVO(int seq, String name, int upperSeq, String url, int depth, int sort, String useYn, String postYn, Date createdAt, List<CategoryPermissionVO> permission, String midCategory) {
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
    this.midCategory = midCategory;
  }
}
