package com.vogue.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vogue.base.domain.CategoryVO;
import com.vogue.base.mapper.BaseMapper;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

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

    try {
      // 상위 카테고리, depth : 1
      JSONArray upperCategory = new JSONArray();

      // 하위 카테고리, depth : 2
      JSONArray subCategory = new JSONArray();

      // 정렬된 카테고리 (상위 -> 하위 [])
      JSONObject aaa = new JSONObject();

      for(CategoryVO vo : categoryAll) {

        JSONObject obj1 = new JSONObject();

        obj1.put("label", vo.getName());
        obj1.put("value", vo.getSeq());
        obj1.put("depth", vo.getDepth());
        obj1.put("postYn", vo.getPostYn());

        upperCategory.add(obj1);

        // JSON parsing 객체 생성
        JSONParser jsonParser = new JSONParser();
        //JSONParser 객체를 통해 String을 JSON 형식으로 변경
        Object json = jsonParser.parse(vo.getMidCategory());
        // 길이가 0, 1이어도 배열로 넘어오는 쿼리기 때문에 JSONArray로 캐스팅
        JSONArray jsonArray = (JSONArray) json;

        for (Object obj : jsonArray) {
          JSONObject jsonObj = (JSONObject) obj;

          if ((long) jsonObj.get("upperSeq") == vo.getSeq()) {

            JSONObject obj2 = new JSONObject();

            obj2.put("label", jsonObj.get("name"));
            obj2.put("value", jsonObj.get("seq"));
            obj2.put("upperSeq", jsonObj.get("upperSeq"));
            obj2.put("depth", jsonObj.get("depth"));
            obj2.put("postYn", jsonObj.get("postYn"));

            subCategory.add(obj2);
          }
        }
      }
      response.put("parent", upperCategory);
      response.put("children", subCategory);
      response.put("tree", baseMapper.getTreeMenu());
      response.put("category", baseMapper.getAsideMenu(idntfCd));

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    // 권한 추가
    response.put("permission", baseMapper.getAuthPermission());

    return response;
  }
}
