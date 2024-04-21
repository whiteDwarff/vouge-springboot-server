package com.vogue.base.service;


import com.vogue.base.domain.CategoryVO;
import com.vogue.base.mapper.CategoryMapper;
import com.vogue.code.CategoryStatus;
import com.vogue.common.CmmnResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryMapper categoryMapper) {
    this.categoryMapper = categoryMapper;
  }
  @Override
  public CmmnResponse InsertCategory(CategoryVO vo) {

    CmmnResponse response = new CmmnResponse();

    int result = categoryMapper.duplicateName(vo);
    String message = CategoryStatus.CREATED.getMessage();

    if(result > 0) message = CategoryStatus.CONFLICT_NAME.getMessage();
    else categoryMapper.insertCategory(vo);

    response.setMessage(message);
    response.put("result", result);

    return response;

  }
}
