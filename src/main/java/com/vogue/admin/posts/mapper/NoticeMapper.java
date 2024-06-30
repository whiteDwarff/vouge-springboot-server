package com.vogue.admin.posts.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NoticeMapper {
  /**
   * 템플릿 등록
   * @params HashMap
   * */
  int insertNotice(HashMap<String, Object> param) throws Exception;
  /**
   * 템플릿 수정
   * @params HashMap
   * */
  int updateNotice(HashMap<String, Object> param) throws Exception;
  /**
   * 말머리 등록
   * @params HashMap
   * */
  void insertPrepend(HashMap<String, Object> param) throws Exception;
  /**
   * 말머리 삭제
   * @params HashMap
   * */
  void deletePrepend(HashMap<String, Object> param) throws Exception;
  /**
   * 말머리 조회
   * @params HashMap
   * @return List
   * */
  List<String> selectOnePrepend(HashMap<String, Object> param) throws Exception;
  /**
   * 템플릿 상세 조회
   * @params HashMap
   * @return HashMap
   * */
  HashMap<String, Object> selectOneNotice(HashMap<String, Object> param) throws Exception;
  /**
   * 템플릿 개수 조회
   * @params HashMap
   * @return int
   * */
  int selectNoticeCount(HashMap<String, Object> param) throws Exception;
  /**
   * 템플릿 목록 조회
   * @params HashMap
   * @return List
   * */
  List<HashMap<String, Object>> selectNoticeList(HashMap<String, Object> param) throws Exception;
  /**
   * 템플릿 삭제
   * @params HashMap
   * */
  void deleteNotice(HashMap<String, Object> param) throws Exception;
  /**
   * 템플릿 수정 시 UPPER_SEQ, LOWER_SEQ와 일치하는 모든 공지사항 USE_YN 변경
   * @params HashMap
   * */
  void updateNoticeUseYn(HashMap<String, Object> param) throws Exception;


}
