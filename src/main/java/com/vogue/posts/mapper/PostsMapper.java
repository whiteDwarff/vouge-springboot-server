package com.vogue.posts.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

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
}
