package com.vogue.admin.schedule.service;

import com.vogue.admin.schedule.mapper.ScheduleMapper;
import com.vogue.code.ScheduleStatus;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Service
public class ScheduleMapperImpl implements ScheduleService{

  private final ScheduleMapper mapper;

  public ScheduleMapperImpl(ScheduleMapper mapper) {
    this.mapper = mapper;
  }


  @Override
  public BaseResponse getScheduleList(HashMap<String, Object> param) throws Exception {

    HashMap<String, Object> map = new HashMap<>();
    map.put("events", mapper.getScheduleList(param));

    return BaseResponse.builder()
            .status(HttpStatus.OK)
            .list(map)
            .build();
  }

  @Override
  public BaseResponse getSchedule(HashMap<String, Object> param) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    map.put("event", mapper.getSchedule(param));

    return BaseResponse.builder()
            .status(HttpStatus.OK)
            .list(map)
            .build();
  }

  @Override
  public BaseResponse saveNotice(HashMap<String, Object> param) throws Exception {

    HttpStatus status = null;
    String message = null;
    int result = 0;

    // 사용자의 PK가 같이 넘어오지 않았을 때 -> error 반환
    if(param.get("author").equals("") || !param.containsKey("author")) {

      status = ScheduleStatus.UNAUTHORIZED.getCode();
      message = ScheduleStatus.UNAUTHORIZED.getMessage();

    // 서비스 로직 실행
    } else {
      result = Objects.nonNull(param.get("id")) ?
              mapper.insertSchedule(param) : 0 /* update */;

      // 등록 및 수정 성공
      if(result > 0) {
        status = ScheduleStatus.OK.getCode();
        message = ScheduleStatus.OK.getMessage();
      // 실패
      } else {
        status = ScheduleStatus.UNAUTHORIZED.getCode();
        message = ScheduleStatus.UNAUTHORIZED.getMessage();
      }
    }

    return BaseResponse.builder()
            .status(status)
            .message(message)
            .build();
  }
}
