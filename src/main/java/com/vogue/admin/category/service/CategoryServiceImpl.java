package com.vogue.admin.category.service;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import com.vogue.admin.category.mapper.CategoryMapper;
import com.vogue.base.domain.CategoryVO;
import com.vogue.code.CategoryStatus;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryMapper categoryMapper) {
    this.categoryMapper = categoryMapper;
  }


  public CmmnResponse InsertCategory(CategoryVO vo) throws Exception {

    CmmnResponse response = new CmmnResponse();

    int result = categoryMapper.duplicateName(vo);
    int seq = 0;
    String message = CategoryStatus.CREATED.getMessage();

    if (result > 0) message = CategoryStatus.CONFLICT_NAME.getMessage();
    else {
      // 카테고리 저장 후 seq 값 반환
      seq = categoryMapper.insertCategory(vo);
      // permission group 할당
      List<CategoryPermissionVO> list = vo.getPermission();
      // category seq 셋팅 후 DB 저장
      for (CategoryPermissionVO item : list) {
        item.setCategorySeq(seq);
        categoryMapper.insertCategoryPermissionGroup(item);
      }
    }

    response.setMessage(message);
    response.put("result", result);

    return response;
  }

  @Override
  public CmmnResponse selectCategoryInfo(int seq) throws Exception {

    CmmnResponse response = new CmmnResponse();

    CategoryVO category = categoryMapper.selectOneCategory(seq);
    category.setPermission(categoryMapper.selectOneCategoryPermission(seq));

    response.put("form", category);

    return response;
  }

  @Override
  public CmmnResponse updateCategory(CategoryVO vo) throws Exception {

    CmmnResponse response = new CmmnResponse();

    int result = categoryMapper.updateCategory(vo);
    String message = CategoryStatus.UPDATE.getMessage();

    if (result < 1) message = CategoryStatus.INTERNAL_SERVER_ERROR_UPDATE.getMessage();
    else {
      // 해당 permission 삭제
      categoryMapper.deleteCategoryPermissionGroup(vo);
      // permission group 할당
      List<CategoryPermissionVO> list = vo.getPermission();
      // category seq 셋팅 후 DB 저장
      for (CategoryPermissionVO item : list) {
        item.setCategorySeq(vo.getSeq());
        categoryMapper.insertCategoryPermissionGroup(item);
      }
    }

    response.put("result", result);
    response.setMessage(message);
  return response;
  }

  @Override
  public CmmnResponse deleteCategory(CategoryVO vo) throws Exception {

    CmmnResponse response = new CmmnResponse();

    String message = CategoryStatus.DELETE.getMessage();

    // 권한 삭제 (FOREIGN KEY)
    int result = categoryMapper.deleteCategoryPermissionGroup(vo);
    // 카테고리 삭제 (PRIMARY KEY)
    result += categoryMapper.deleteCategory(vo);

    if(result < 1)
      message = CategoryStatus.INTERNAL_SERVER_ERROR_DELETE.getMessage();

    response.setMessage(message);
    response.put("result", result);

    return response;
  }
}
