package com.vogue.base.mapper;

import com.vogue.base.domain.AuthPermissionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BaseMapper {
  /**
   * 서비스 메뉴 검색
   * @params HashMap
   * @return List
   * */
  List<HashMap<String, Object>>getAsideMenu(HashMap<String, Object> param) throws Exception;
  /**
   * 트리 목록 리스트 반환
   * @params void
   * @return List
   * */
  List<HashMap<String, Object>> getTreeMenu() throws Exception;
  /**
   * 모든 권한 검색
   * @params void
   * @return List
   * */
  List<AuthPermissionVO> getAuthPermission() throws Exception;
  /**
   * 하위 카테고리와 일치한 게시판 템플릿 검색
   * @params Object
   * @return HashMap
   * */
  HashMap<String, String> getTemplateBySeq(Object param) throws Exception;
  /**
   * 하위 카테고리와 일치한 게시판 말머리 검색
   * @params Object
   * @return HashMap
   * */
  List<String> getPrependBySeq(Object param) throws Exception;

}
