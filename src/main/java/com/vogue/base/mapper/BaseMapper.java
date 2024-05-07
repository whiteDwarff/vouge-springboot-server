package com.vogue.base.mapper;


import com.vogue.base.domain.AuthPermissionVO;
import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BaseMapper {
  List<CategoryVO>getAsideMenu(String idntfCd) throws Exception;

  List<HashMap<String, Object>> getTreeMenu() throws Exception;

  List<AuthPermissionVO> getAuthPermission() throws Exception;

}
