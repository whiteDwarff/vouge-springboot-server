package com.vogue.base.mapper;


import com.vogue.base.domain.CategoryPermissionVO;
import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Mapper
public interface CategoryMapper {
  // List<CategoryVO> getCategory();

  int duplicateName(CategoryVO vo);

  int insertCategory(CategoryVO vo);

  void insertCategoryPermissionGroup(CategoryPermissionVO vo);

  List<CategoryVO>getCategory();
}
