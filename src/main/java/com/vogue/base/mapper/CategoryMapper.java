package com.vogue.base.mapper;


import com.vogue.base.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
  List<CategoryVO> getCategory();
}
