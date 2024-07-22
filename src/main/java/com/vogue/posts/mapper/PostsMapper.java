package com.vogue.posts.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PostsMapper {
  /**
   * 게시글 등록
   * @params HashMap
   * */
  void insertPosts(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 상세 조회
   * @params HashMap
   * @return HashMap
   * */
  HashMap<String, Object> selectOne(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 목록 조회
   * @params HashMap
   * @return List
   * */
  List<HashMap<String, Object>> selectByPaging(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 목록 개수 조회
   * @params HashMap
   * @return int
   * */
  int selectByPagingCount(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 삭제
   * @params HashMap
   * */
  void delete(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 수정 > 게시글 상세 조회
   * @params HashMap
   * @return HashMap
   * */
  HashMap<String, Object> selectEditInfo(HashMap<String, Object> param) throws Exception;
}
