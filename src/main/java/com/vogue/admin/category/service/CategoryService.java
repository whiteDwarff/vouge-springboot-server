package com.vogue.admin.category.service;

import com.vogue.base.domain.CategoryVO;
import com.vogue.common.CmmnResponse;

public interface CategoryService {

  CmmnResponse InsertCategory(CategoryVO vo) throws Exception;

  CmmnResponse selectCategoryInfo(int seq) throws Exception;

  CmmnResponse updateCategory(CategoryVO vo) throws Exception;

  CmmnResponse deleteCategory(CategoryVO vo) throws Exception;

}
