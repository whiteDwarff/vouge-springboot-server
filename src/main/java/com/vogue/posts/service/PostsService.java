package com.vogue.posts.service;

import com.vogue.common.BaseResponse;

import java.util.HashMap;

public interface PostsService {
  /**
   * 게시들 등록 및 수정
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse save(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectOne(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectByPaging(HashMap<String, Object> param) throws Exception;
}
