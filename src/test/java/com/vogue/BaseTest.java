package com.vogue;


import com.vogue.base.domain.CategoryVO;
import com.vogue.base.domain.PermissionVO;
import com.vogue.base.mapper.CategoryMapper;
import com.vogue.base.mapper.PermissionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BaseTest {

  @Autowired
  PermissionMapper p_mapper;
  @Autowired
  CategoryMapper c_mapper;

  @Test
  @DisplayName("사용자의 권한 가져오기")
  void getPermission(){
    List<PermissionVO> list = p_mapper.getPermission();

    for(PermissionVO vo : list) {
      System.out.println(vo.toString());
    }
  }

  @Test
  @DisplayName("카테고리 조회")
  void getCategory() {
    List<CategoryVO> list = c_mapper.getCategory();
  }

}
