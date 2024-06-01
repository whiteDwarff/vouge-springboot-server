package com.vogue.admin.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface ScheduleMapper {

  int insertSchedule(HashMap<String, Object> param) throws Exception;
}
