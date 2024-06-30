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
   * 일정 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("get")
  public BaseResponse getScheduleList(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/schedule/get : " + param.toString());

    return scheduleService.getScheduleList(param);
  }
  /**
   * 일정 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("selectOne")
  public BaseResponse getSchedule(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/schedule/selectOne : " + param.toString());

    return scheduleService.getSchedule(param);
  }
  /**
   * 일정 등록 및 수정
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("save")
  public BaseResponse saveSchedule(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/schedule/save : " + param.toString());

    return scheduleService.saveSchedule(param);
  }
  /**
   * 일정 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("delete")
  public BaseResponse deleteSchedule(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/schedule/delete : " + param.toString());

    return scheduleService.deleteSchedule(param);
  }
}
