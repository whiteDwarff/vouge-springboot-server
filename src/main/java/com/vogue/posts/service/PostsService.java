package com.vogue.posts.service;

import com.vogue.common.BaseResponse;

import java.util.HashMap;

public interface PostsService {
  /**
   * 게시들 등록
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse savePosts(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectOne(HashMap<String, Object> param) throws Exception;

}
