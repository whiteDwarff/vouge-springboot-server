package com.vogue.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vogue.base.domain.CategoryVO;
import com.vogue.base.mapper.BaseMapper;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class BaseServiceImpl implements BaseService {

  private final BaseMapper baseMapper;

  public BaseServiceImpl(BaseMapper baseMapper) {
    this.baseMapper = baseMapper;
  }
  @Override
  public CmmnResponse getSystemMenu(String idntfCd) throws Exception {

    CmmnResponse response = new CmmnResponse();

    List<CategoryVO> categoryAll = baseMapper.getAsideMenu(idntfCd);

    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    try {
      // 상위 카테고리, depth : 1
      ArrayList<HashMap<String, Object>> upperCategory = new ArrayList<>();

      // 하위 카테고리, depth : 2
      ArrayList<HashMap<String, Object>> subCategory = new ArrayList<>();

      for(CategoryVO category : categoryAll) {
        HashMap<String, Object> map1 = new HashMap<>();
        ArrayList<CategoryVO> children = new ArrayList<>();

        // 상위 카테고리 추가
        map1.put("label", category.getName());
        map1.put("value", category.getSeq());
        upperCategory.add(map1);

        CategoryVO[] item = objectMapper.readValue(category.getMidCategory(), CategoryVO[].class);

        for(CategoryVO vo : item) {
          if(vo.getUpperSeq() == category.getSeq()) {
            children.add(vo);
            category.setChildren(children);

            HashMap<String, Object> map2 = new HashMap<>();

            // 하위 카테고리 추가
            map2.put("label", vo.getName());
            map2.put("value", vo.getSeq());
            map2.put("upperSeq", vo.getUpperSeq());
            subCategory.add(map2);
          }
        }
        category.setMidCategory("");
      }
      response.put("parent", upperCategory);
      response.put("children", subCategory);
      response.put("tree", baseMapper.getTreeMenu());


    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    response.put("category", baseMapper.getAsideMenu(idntfCd));

    return response;
  }
}
