package com.vogue.admin.posts.service;

import com.vogue.admin.posts.domain.NoticeVO;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;

import java.util.HashMap;
import java.util.List;

public interface  NoticeService{

  BaseResponse saveNotice(HashMap<String, Object> param) throws Exception;

  BaseResponse getPostsList(HashMap <String, Object> param) throws Exception;

  BaseResponse selectOneNotice(Long seq) throws Exception;

  BaseResponse deleteNotice(List<HashMap <String, Object>> param) throws Exception;

}
