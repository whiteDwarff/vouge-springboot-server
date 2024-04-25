package com.vogue.base.mapper;

import com.vogue.base.domain.AuthPermissionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
  List<AuthPermissionVO> getPermission();
}
