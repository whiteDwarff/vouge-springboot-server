package com.vogue.admin.category.mapper;

import com.vogue.admin.category.domain.CategoryPermissionVO;
import com.vogue.base.domain.AuthPermissionVO;
import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CategoryMapper {
  int duplicateName(CategoryVO vo) throws Exception;

  int insertCategory(CategoryVO vo) throws Exception;

  void insertCategoryPermissionGroup(CategoryPermissionVO vo) throws Exception;

  List<CategoryVO> getCategoryAll() throws Exception;

  List<HashMap<String, Object>> getPermissionALl() throws Exception;

  List<HashMap<String, Object>> getSelectOption() throws Exception;
}
