package com.vogue.posts.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LikedMapper {
  /**
   * 게시글 좋아요 등록
   * @params HashMap
   * */
  void insert(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 좋아요 여부 확인
   * @params HashMap
   * @return int
   * */
  int selectOne(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 좋아요 삭제
   * @params HashMap
   * */
  void delete(HashMap<String, Object> param) throws Exception;
}
