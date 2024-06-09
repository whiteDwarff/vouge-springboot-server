package com.vogue.admin.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ScheduleMapper {

  List<HashMap<String, Object>> getScheduleList(HashMap<String, Object> param) throws Exception;

  HashMap<String, Object> getSchedule(HashMap<String, Object> param) throws Exception;

  int insertSchedule(HashMap<String, Object> param) throws Exception;

  int updateSchedule(HashMap<String, Object> param) throws Exception;

  int deleteSchedule(HashMap<String, Object> param) throws Exception;
}
