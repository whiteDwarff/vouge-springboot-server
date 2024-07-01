package com.vogue.admin.schedule.service;

import com.vogue.admin.schedule.mapper.ScheduleMapper;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{

  private final ScheduleMapper mapper;

  public ScheduleServiceImpl(ScheduleMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 일정 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse getScheduleList(HashMap<String, Object> param) throws Exception {

    HashMap<String, Object> map = new HashMap<>();
    map.put("events", mapper.getScheduleList(param));

    return BaseResponse.BaseCodeBuilder()
            .result(map)
            .build();
  }
  /**
   * 일정 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse getSchedule(HashMap<String, Object> param) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    map.put("event", mapper.getSchedule(param));

    return BaseResponse.BaseCodeBuilder()
            .result(map)
            .build();
  }
  /**
   * 일정 등록 및 수정
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse saveSchedule(HashMap<String, Object> param) throws Exception {

    HttpStatus status = HttpStatus.OK;
    HashMap<String, Object> map = new HashMap<>();

    try {
      if(!param.get("author").equals("") || param.containsKey("author")) {
        if(param.get("id").equals("")) {
          int id = mapper.insertSchedule(param);
          map.put("id", id);
          map.put("event", mapper.getSchedule(map));
        }
        else {
          mapper.updateSchedule(param);
          map.put("event", mapper.getSchedule(param));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      status = HttpStatus.BAD_REQUEST;
    }

    return BaseResponse.BaseCodeBuilder()
            .status(status)
            .result(map)
            .build();
  }
  /**
   * 일정 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse deleteSchedule(HashMap<String, Object> param) throws Exception {

    HttpStatus status = HttpStatus.OK;

    try {
      if(!param.get("id").equals("")) mapper.deleteSchedule(param);
    } catch (Exception e) {
      e.printStackTrace();
      status = HttpStatus.BAD_REQUEST;
    }

    return BaseResponse.BaseCodeBuilder()
            .status(status)
            .build();
  }
}
