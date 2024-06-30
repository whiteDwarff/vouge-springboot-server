package com.vogue.admin.posts.service;

import com.vogue.common.BaseResponse;

import java.util.HashMap;
import java.util.List;

public interface  NoticeService{

  BaseResponse saveNotice(HashMap<String, Object> param) throws Exception;
  /**
   * 게시판의 템플릿, 공지사항, 말머리 조회
   * @params HashMap
   * @return BaseResponse
   * */
  BaseResponse getPostsList(HashMap <String, Object> param) throws Exception;

  BaseResponse selectOneNotice(HashMap<String, Object> param) throws Exception;

  BaseResponse deleteNotice(List<HashMap <String, Object>> param) throws Exception;

}
