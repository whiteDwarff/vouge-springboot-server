package com.vogue.base.mapper;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface baseMapper {
  List<CategoryVO>getAsideMenu(String idntfCd);
}
