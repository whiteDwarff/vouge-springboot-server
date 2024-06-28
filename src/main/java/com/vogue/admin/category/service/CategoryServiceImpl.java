package com.vogue.admin.category.service;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import com.vogue.admin.category.mapper.CategoryMapper;
import com.vogue.base.domain.CategoryVO;
import com.vogue.code.CategoryStatus;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryMapper categoryMapper) {
    this.categoryMapper = categoryMapper;
  }

  /**
   * PK를 통해 카테고리 정보 검색
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse selectCategoryInfo(HashMap<String, Object> param) throws Exception {

    HashMap<String, Object> category = categoryMapper.selectOneCategory(param);
    category.put("permission", categoryMapper.selectOneCategoryPermission(param));

    return BaseResponse.BaseCodeBuilder()
              .result(category)
              .build();
  }
  /**
   * 카테고리 저장 및 수정
   * @params  CategoryVO
   * @return  BaseResponse
   * */
  @Override
  @Transactional
  public BaseResponse saveCategory(CategoryVO vo) throws Exception {

    HttpStatus status = null;
    boolean isState =  0 < vo.getSeq();

    try {
      // 등록
      if(!isState) {
         // 카테고리 저장 후 seq 값 반환
         int seq  = categoryMapper.insertCategory(vo);
         // permission group 할당
         List<CategoryPermissionVO> list = vo.getPermission();
         // category seq 셋팅 후 DB 저장
         for (CategoryPermissionVO item : list) {
            item.setCategorySeq(seq);
            categoryMapper.insertCategoryPermissionGroup(item);
          }
      // 수정
      } else {
        categoryMapper.updateCategory(vo);
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
      status = HttpStatus.OK;
    } catch (Exception e) {
      e.printStackTrace();
      status = HttpStatus.BAD_REQUEST;
    }

   return BaseResponse.BaseCodeBuilder()
           .status(status)
           .build();
  }
  /**
   * 카테고리, 권한, 템플릿 삭제
   * @params HashMap
   * @return  BaseResponse
   * */
  @Override
  public BaseResponse deleteCategory(HashMap<String, Object> param) throws Exception {

    HttpStatus status = HttpStatus.OK;
    try {
      categoryMapper.deleteCategory(param);
    } catch (Exception e) {
      e.printStackTrace();
      status = HttpStatus.BAD_REQUEST;
    }

    return BaseResponse.BaseCodeBuilder()
            .status(status)
            .build();
  }
}
