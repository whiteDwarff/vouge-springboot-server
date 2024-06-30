package com.vogue.admin.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ScheduleMapper {
  /**
   * 일정 목록 조회
   * @params HashMap
   * @return List
   * */
  List<HashMap<String, Object>> getScheduleList(HashMap<String, Object> param) throws Exception;
  /**
   * 일정 상세 조회
   * @params HashMap
   * @return HashMap
   * */
  HashMap<String, Object> getSchedule(HashMap<String, Object> param) throws Exception;
  /**
   * 일정 등록 후 ID 반환
   * @params HashMap
   * @return int
   * */
  int insertSchedule(HashMap<String, Object> param) throws Exception;
  /**
   * 일정 수정
   * @params HashMap
   * */
  void updateSchedule(HashMap<String, Object> param) throws Exception;
  /**
   * 일정 삭제
   * @params HashMap
   * */
  void deleteSchedule(HashMap<String, Object> param) throws Exception;
}
