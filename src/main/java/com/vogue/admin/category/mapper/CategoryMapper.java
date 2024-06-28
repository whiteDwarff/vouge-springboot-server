package com.vogue.admin.category.mapper;

import com.vogue.admin.category.domain.CategoryPermissionVO;
import com.vogue.base.domain.AuthPermissionVO;
import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CategoryMapper {
  /**
   * 카테고리 등록 후 RETURNING을 통해 SEQ 반환
   * @params CategoryVO
   * @return int
   * */
  int insertCategory(CategoryVO vo) throws Exception;
  /**
   * 카테고리 권한 등록
   * @params CategoryVO
   * */
  void insertCategoryPermissionGroup(CategoryPermissionVO vo) throws Exception;
  /**
   * 카테고리 정보 수정
   * @params CategoryVO
   * */
  void updateCategory(CategoryVO vo) throws Exception;
  /**
   * 카테고리 권한 조회
   * @params HashMap
   * @return List<HashMap>
   * */
  List<HashMap<String, Object>> selectOneCategoryPermission(HashMap<String, Object> param) throws Exception;
  /**
   * 카테고리 상세 조회
   * @params HashMap
   * @return HashMap
   * */
  HashMap<String, Object> selectOneCategory(HashMap<String, Object> param) throws Exception;
  /**
   * 카테고리 권한 삭제
   * @params CategoryVO
   * */
  void deleteCategoryPermissionGroup(CategoryVO vo) throws Exception;
  /**
   * 카테고리, 템플릿 삭제
   * @params HashMap
   * */
  void deleteCategory(HashMap<String, Object> param) throws Exception;
}
