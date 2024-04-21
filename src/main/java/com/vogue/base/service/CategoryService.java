package com.vogue.base.service;

import com.vogue.base.domain.CategoryVO;
import com.vogue.common.CmmnResponse;

public interface CategoryService {

  CmmnResponse InsertCategory(CategoryVO vo);
}
