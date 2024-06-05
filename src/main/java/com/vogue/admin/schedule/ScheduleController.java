package com.vogue.admin.schedule;


import com.vogue.admin.schedule.service.ScheduleService;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/admin/schedule/*")
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }


  /**
   * @params     : HashMap
   * @Exception : throws Exception
   * @return    : ResponseEntity
   * 일정 목록  조회
   * */
  @PostMapping("get")
  public BaseResponse getScheduleList(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /api/admin/schedule/get : " + param.toString());

    return scheduleService.getScheduleList(param);
  }/**
   * @params     : HashMap
   * @Exception : throws Exception
   * @return    : ResponseEntity
   * 일정 상세조회
   * */
  @PostMapping("selectOne")
  public BaseResponse getSchedule(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /api/admin/schedule/selectOne : " + param.toString());

    return scheduleService.getSchedule(param);
  }
  /**
   * @params     : HashMap
   * @Exception : throws Exception
   * @return    : ResponseEntity
   * 일정 등록 및 수정
   * */
  @PostMapping("save")
  public BaseResponse insertSchedule(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /api/admin/schedule/save : " + param.toString());

    return scheduleService.saveNotice(param);
  }
}
