package com.vogue.admin.category.service;

import com.vogue.base.domain.CategoryVO;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;

import java.util.HashMap;

public interface CategoryService {

  CmmnResponse InsertCategory(CategoryVO vo) throws Exception;
  /**
   * PK를 통해 카테고리 정보 검색
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectCategoryInfo(HashMap<String, Object> param) throws Exception;

  CmmnResponse updateCategory(CategoryVO vo) throws Exception;

  CmmnResponse deleteCategory(CategoryVO vo) throws Exception;

}
