package com.vogue.admin.category;

import com.vogue.admin.category.service.CategoryService;
import com.vogue.base.domain.CategoryVO;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("api/admin/category/*")
@RestController
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * PK를 통해 카테고리 정보 검색
   * @params HashMap
   * @result BaseResponse
   * */
  @PostMapping("selectOne")
  public BaseResponse selectCategoryInfo(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/category/selectOne : " + param.toString());

    return categoryService.selectCategoryInfo(param);
  }
  /**
   * 카테고리 저장, 수정
   * @params CategoryVO
   * @result BaseResponse
   * */
  @PostMapping("save")
  public BaseResponse saveCategory(@RequestBody CategoryVO vo) throws Exception{

    log.info("POST : /admin/category/update : " + vo.toString());

    return categoryService.saveCategory(vo);
  }
  /**
   * 카테고리, 권한, 템플릿 삭제
   * @params HashMap
   * @return  BaseResponse
   * */
  @PostMapping("delete")
  public BaseResponse deleteCategory(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /admin/category/delete : " + param.toString());

    return categoryService.deleteCategory(param);

  }
}
