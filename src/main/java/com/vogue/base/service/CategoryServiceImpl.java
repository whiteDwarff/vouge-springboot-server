//package com.vogue.base.service;
//
//
//import com.vogue.admin.category.domain.CategoryPermissionVO;
//import com.vogue.base.domain.CategoryVO;
//import com.vogue.base.mapper.CategoryMapper;
//import com.vogue.code.CategoryStatus;
//import com.vogue.common.CmmnResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Slf4j
//@Service
//public class CategoryServiceImpl implements CategoryService{
//
//  private final CategoryMapper categoryMapper;
//
//  public CategoryServiceImpl(CategoryMapper categoryMapper) {
//    this.categoryMapper = categoryMapper;
//  }
//
//  @Override
//  public CmmnResponse InsertCategory(CategoryVO vo) {
//
//    CmmnResponse response = new CmmnResponse();
//
//    int result = categoryMapper.duplicateName(vo);
//    int seq = 0;
//    String message = CategoryStatus.CREATED.getMessage();
//
//    if(result > 0) message = CategoryStatus.CONFLICT_NAME.getMessage();
//    else {
//      // 카테고리 저장 후 seq 값 반환
//      seq = categoryMapper.insertCategory(vo);
//      // permission group 할당
//      List<CategoryPermissionVO> list = vo.getPermission();
//      // category seq 셋팅 후 DB 저장
//      for(CategoryPermissionVO item : list) {
//        item.setCategorySeq(seq);
//        categoryMapper.insertCategoryPermissionGroup(item);
//      }
//    }
//
//    response.setMessage(message);
//    response.put("result", result);
//
//    return response;
//  }
//}
