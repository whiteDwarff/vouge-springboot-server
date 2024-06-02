package com.vogue.admin.schedule.service;

import com.vogue.common.BaseResponse;

import java.util.HashMap;

public interface ScheduleService {


  BaseResponse getScheduleList(HashMap<String, Object> param) throws Exception;

  BaseResponse saveNotice(HashMap<String, Object> param) throws Exception;

}
