package com.vogue.base.mapper;


import com.vogue.base.domain.AuthPermissionVO;
import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BaseMapper {
  List<CategoryVO>getAsideMenu(String idntfCd) throws Exception;

  List<HashMap<String, Object>> getTreeMenu() throws Exception;

  List<AuthPermissionVO> getAuthPermission() throws Exception;
  /**
   * 하위 카테고리와 일치한 게시판 템플릿 검색
   * @param Object
   * @return HashMap
   * */
  HashMap<String, String> getTemplateBySeq(Object param) throws Exception;
  /**
   * 하위 카테고리와 일치한 게시판 말머리 검색
   * @param Object
   * @return HashMap
   * */
  List<String> getPrependBySeq(Object param) throws Exception;

}
