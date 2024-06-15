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
import org.thymeleaf.util.MapUtils;

import java.lang.reflect.Array;
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

    try {
      // 상위 카테고리, depth : 1
      JSONArray upperCategory = new JSONArray();

      // 하위 카테고리, depth : 2
      JSONArray subCategory = new JSONArray();

      // -----------------------------------------------------------
      // depth가 1인 카테고리 목록 셋팅 ( parent )
      // -----------------------------------------------------------
      for(CategoryVO vo : categoryAll) {
        // 관리자가 메뉴가 아닌 게시판 사용여부가 Y인 메뉴만 저장
        if(vo.getPostYn().equals("Y")) {
          JSONObject obj1 = new JSONObject();
          obj1.put("label", vo.getName());
          obj1.put("value", vo.getSeq());
          obj1.put("depth", vo.getDepth());
          obj1.put("postYn", vo.getPostYn());

          upperCategory.add(obj1);
        }
        // -----------------------------------------------------------
        // depth가 2인 카테고리 목록 셋팅 ( parent )
        // -----------------------------------------------------------
        // JSON parsing 객체 생성
        JSONParser jsonParser = new JSONParser();
        //JSONParser 객체를 통해 String 형식의 JSON 데이터를 JSON 형식으로 변경
        Object json = jsonParser.parse(vo.getMidCategory());
        // 길이가 0, 1이어도 배열로 넘어오는 쿼리기 때문에 JSONArray로 캐스팅
        JSONArray jsonArray = (JSONArray) json;

        for (Object obj : jsonArray) {
          JSONObject jsonObj = (JSONObject) obj;
          // obj의 upperSeq와 vo의 seq를 비교하여 하위 카테고리 확인, 게시판 여부를 확인하여 데이터 셋팅
          if ((long) jsonObj.get("upperSeq") == vo.getSeq() && ((JSONObject) obj).get("postYn").equals("Y")) {
            log.info("############ OBJ : " + obj.toString());

            JSONObject category = new JSONObject();
            // 하위 카테고리로 등록된 게시판 템플릿 걸색
            HashMap<String, String> template = baseMapper.getTemplateBySeq(obj);
            // 등록된 템플릿이 있다면
            if(template != null) {
              category.put("template", emptyCheckByTemplate(template.get("template")));
              category.put("prepend", baseMapper.getPrependBySeq(template));
            } else {
              category.put("template", "");
              category.put("prepend", new Array[0]);
            }
            category.put("label", jsonObj.get("name"));
            category.put("value", jsonObj.get("seq"));
            category.put("upperSeq", jsonObj.get("upperSeq"));
            category.put("depth", jsonObj.get("depth"));
            category.put("postYn", jsonObj.get("postYn"));

            subCategory.add(category);
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

  public String emptyCheckByTemplate(String template) {
    return template == null || template.isEmpty() ? "" : template;
  }

}
