package com.vogue.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vogue.base.domain.CategoryVO;
import com.vogue.base.mapper.BaseMapper;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BaseServiceImpl implements BaseService {

  private final BaseMapper baseMapper;

  public BaseServiceImpl(BaseMapper baseMapper) {
    this.baseMapper = baseMapper;
  }

  /**
   * 사용자의 권한에 맞는 메뉴 리스트 반환
   * @params param
   * @return BaseResponse
   * */

  @Override
  public BaseResponse getSystemMenu(HashMap<String, Object> param) throws Exception {

    List<HashMap<String, Object>> categoryAll = baseMapper.getAsideMenu(param);

    HashMap<String, Object> menuMap = new HashMap<>();
    HttpStatus status = HttpStatus.OK;
    try {
      menuMap.put("parent", new JSONArray());
      menuMap.put("children", new JSONArray());
      menuMap.put("permission", baseMapper.getAuthPermission());
      menuMap.put("tree", baseMapper.getTreeMenu());
      setUpperMenuList(menuMap, categoryAll);
    } catch (Exception e) {
      e.printStackTrace();
      status = HttpStatus.BAD_REQUEST;
    }

    return BaseResponse.builder()
            .status(status)
            .result(menuMap)
            .build();
  }
  /**
   * 상위 카테고리 추가
   * @params  HashMap, List
   * @return  void
   * */
  public void setUpperMenuList(HashMap<String, Object> menuMap, List<HashMap<String, Object>> categoryAll) throws ParseException {
    JSONArray category = new JSONArray();
    HashMap<String, Object> menu = new HashMap<>();

    // depth가 1인 카테고리 목록 셋팅 ( parent )
    for (HashMap<String, Object> map: categoryAll) {
        JSONObject obj = new JSONObject();
        obj.put("name", map.get("name"));
        obj.put("seq", map.get("seq"));
        obj.put("depth", map.get("depth"));
        obj.put("postYn", map.get("postYn"));
        obj.put("midCategory", new JSONArray());

        // JSON parsing 객체 생성
        JSONParser jsonParser = new JSONParser();
        //JSONParser 객체를 통해 String 형식의 JSON 데이터를 JSON 타입으로 캐스팅
        Object upperPermissionJson = jsonParser.parse(String.valueOf(map.get("permission")));
        Object lowerPermissionJson = jsonParser.parse(String.valueOf(map.get("midCategory")));

        // 값이 없거나 있어도 배열로 넘어오는 쿼리기 때문에 JSONArray로 캐스팅
        JSONArray upperPermissionArr = (JSONArray) upperPermissionJson;
        JSONArray lowerPermissionArr = (JSONArray) lowerPermissionJson;

        obj.put("permission", upperPermissionArr);
        setLowerMenuList(menuMap, obj, lowerPermissionArr);

        category.add(obj);
        // menuMap에 상위 카테고리 저장
        setMenuMapDiv(menuMap, obj, "parent");
    }
    menuMap.put("menu", category);
  }
  /**
   * 상위 카테고리의 SEQ와 일치하는 하위 카테고리를 찾아 상위카테고리에 추가
   * @params  HashMap, JSONObject, JSONArray
   * @return  void
   * */
  public void setLowerMenuList(HashMap<String, Object> menuMap, JSONObject obj, JSONArray lowerPermissionArr) throws ParseException {

    log.info("ARR  : "  + lowerPermissionArr.toString());
    for(int i=0; i<lowerPermissionArr.size(); i++) {
      // lowerPermissionArr를 순회하는 객체
      JSONObject jsonObj = (JSONObject) lowerPermissionArr.get(i);
      // 데이터를 셋팅하여 저장할 새 객체
      JSONObject newObj = new JSONObject();
      // 상위 카테고리의 SEQ와 하위 카테고리의 UPPER SEQ가 동일한지 비교
      boolean isSame = obj.get("seq").toString().equals(jsonObj.get("upperSeq").toString());
        if(isSame) {
          newObj.put("name", jsonObj.get("name"));
          newObj.put("seq", jsonObj.get("seq"));
          newObj.put("upperSeq", jsonObj.get("upperSeq"));
          newObj.put("depth", jsonObj.get("depth"));
          newObj.put("postYn", jsonObj.get("postYn"));
          newObj.put("url", jsonObj.get("url"));

          JSONParser jsonParser = new JSONParser();
          Object permission = jsonParser.parse(String.valueOf(jsonObj.get("permission")));
          JSONArray permissionArr = (JSONArray) permission;
          newObj.put("permission", permissionArr);

          JSONArray midCategory = (JSONArray) obj.get("midCategory");
          try {
            // 하위 카테고리에 등록된 공지사항과 템플릿이 있다면 저장
            setLowerTemplate(newObj);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
          midCategory.add(newObj);
          // menuMap에 하위 카테고리 저장
          setMenuMapDiv(menuMap, newObj, "children");
        }
    }
  }
  /**
  * 하위 카테고리에 등록된 공지사항과 템플릿이 있다면 저장
  * @params  JSONObject
  * @return  void
  * */
  public void setLowerTemplate(JSONObject obj) throws Exception {
    HashMap<String, String> template = baseMapper.getTemplateBySeq(obj);
    if(template != null) {
      obj.put("notice", emptyCheckByTemplate(template.get("notice")));
      obj.put("template", emptyCheckByTemplate(template.get("template")));
      obj.put("prepend", baseMapper.getPrependBySeq(template));
    } else {
      obj.put("notice", "");
      obj.put("template", "");
      obj.put("prepend", new Array[0]);
    }
  }
  /**
   * menuMap에 div에 맞는 데이터 셋팅
   * @params  HashMap, JSONObject, String
   * @return  void
   * */
  public void setMenuMapDiv(HashMap<String, Object> menuMap, JSONObject obj, String div) {
    JSONObject newObj = new JSONObject();
    newObj.put("label", obj.get("name"));
    newObj.put("value", obj.get("seq"));
    newObj.put("depth", obj.get("depth")) ;
    newObj.put("postYn", obj.get("postYn"));

    JSONArray jsonArr = (JSONArray) menuMap.get(div);
    jsonArr.add(newObj);
  }
  /**
   * 템플릿과 공지사항 유무에 따라 빈값 혹은 값 셋팅
   * @params  String
   * @return  String
   * */
  public String emptyCheckByTemplate(String template) {
    return template == null || template.isEmpty() ? "" : template;
  }
}
