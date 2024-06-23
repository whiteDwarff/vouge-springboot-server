package com.vogue.base.service;

import com.vogue.common.BaseResponse;
import java.util.HashMap;

public interface BaseService {
  /**
   * 카테고리 및 메뉴 리스트 반환
   * @params  HashMap
   * @return  BaseResponse
   * */
  BaseResponse getSystemMenu(HashMap<String, Object> param) throws Exception;
}
