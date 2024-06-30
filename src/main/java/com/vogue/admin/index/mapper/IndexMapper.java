package com.vogue.admin.index.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface IndexMapper {

    int insertPermission(HashMap<String, Object> param) throws Exception;

    int updatePermission(HashMap<String, Object> param) throws Exception;

}
