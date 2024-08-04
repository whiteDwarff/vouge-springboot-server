package com.vogue.posts.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface CommentMapper {
  /**
   * 댓글 등록
   * @params HashMap
   * */
  void insert(HashMap<String, Object> param) throws Exception;
  /**
   * 댓글 삭제
   * @params HashMap
   * */
  void delete(HashMap<String, Object> param) throws Exception;
}
