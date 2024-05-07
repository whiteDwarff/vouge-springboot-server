package com.vogue.admin.posts.service;

import com.vogue.admin.posts.domain.NoticeVO;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;

import java.util.HashMap;

public interface  NoticeService{

  BaseResponse InsertNotice(HashMap<String, Object> param) throws Exception;


  BaseResponse getPostsList(HashMap <String, Object> param) throws Exception;

}
