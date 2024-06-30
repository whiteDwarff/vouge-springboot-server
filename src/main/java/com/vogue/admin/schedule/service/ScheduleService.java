package com.vogue.admin.schedule.service;

import com.vogue.common.BaseResponse;

import java.util.HashMap;

public interface ScheduleService {

  /**
   * 일정 목록 조회
   * @params param
   * @return BaseResponse
   * */
  BaseResponse getScheduleList(HashMap<String, Object> param) throws Exception;
  /**
   * 일정 상세 조회
   * @params param
   * @return BaseResponse
   * */
  BaseResponse getSchedule(HashMap<String, Object> param) throws Exception;
  /**
   * 일정 등록 및 수정
   * @params param
   * @return BaseResponse
   * */
  BaseResponse saveSchedule(HashMap<String, Object> param) throws Exception;

  BaseResponse deleteSchedule(HashMap<String, Object> param) throws Exception;
}
