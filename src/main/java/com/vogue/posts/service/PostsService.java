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
  BaseResponse selectOne(HashMap<String, Object> param) throws Exception;  /**
   * 게시글 수정 > 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectEditInfo(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse selectByPaging(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse delete(HashMap<String, Object> param) throws Exception;
  /**
   * 게시글 좋아요 등록 및 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse toggleLiked(HashMap<String, Object> param) throws Exception;
  /**
   * 댓글 등록
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse addComment(HashMap<String, Object> param) throws Exception;
}
