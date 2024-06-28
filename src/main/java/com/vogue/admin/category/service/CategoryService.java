package com.vogue.admin.category.service;

import com.vogue.base.domain.CategoryVO;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;

import java.util.HashMap;

public interface CategoryService {

  /**
   * PK를 통해 카테고리 정보 검색
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectCategoryInfo(HashMap<String, Object> param) throws Exception;
  /**
   * 카테고리 저장 및 수정
   * @params CategoryVO
   * @return BaseResponse
   * */
  BaseResponse saveCategory(CategoryVO vo) throws Exception;
  /**
   * 카테고리 및 하위 템플릿 삭제
   * @params CategoryVO
   * @return BaseResponse
   * */
  BaseResponse deleteCategory(HashMap<String, Object> param) throws Exception;

}
