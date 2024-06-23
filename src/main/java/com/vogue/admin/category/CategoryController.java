package com.vogue.admin.category;


import com.vogue.admin.category.domain.CategoryPermissionVO;
import com.vogue.admin.category.service.CategoryService;
import com.vogue.base.domain.CategoryVO;
import com.vogue.code.CategoryStatus;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("api/admin/category/*")
@RestController
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping("insert")
  public ResponseEntity<?> insertCategory(@RequestBody CategoryVO vo) throws Exception{

    log.info("/api/admin/category/insert : " + vo.toString());

    List<CategoryPermissionVO> list = vo.getPermission();
    for(CategoryPermissionVO a : list) {
      log.info(a.toString());
    }
    CmmnResponse response = categoryService.InsertCategory(vo);
    int result = (int) response.get("result");

    HttpStatus httpStatus = result > 0 ?
            CategoryStatus.CONFLICT_NAME.getCode() : CategoryStatus.CREATED.getCode();

    return ResponseEntity.status(httpStatus).body(response);
  }
  /**
   * PK를 통해 카테고리 정보 검색
   * params HashMap
   * result BaseResponse
   * */
  @PostMapping("selectOne")
  public BaseResponse selectCategoryInfo(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("/api/admin/category/selectOne : " + param.toString());

    return categoryService.selectCategoryInfo(param);
  }

  @PatchMapping("update")
  public ResponseEntity<?> updateCategory(@RequestBody CategoryVO vo) throws Exception{

    log.info("/api/admin/category/update : " + vo.toString());
    CmmnResponse response = categoryService.updateCategory(vo);

    HttpStatus status = (int) response.get("result") > 0 ?
      CategoryStatus.UPDATE.getCode() : CategoryStatus.INTERNAL_SERVER_ERROR_UPDATE.getCode();

    return ResponseEntity.status(status).body(response);
  }

  @DeleteMapping("delete")
  public ResponseEntity<?> deleteCategory(@RequestParam("seq") int seq) throws Exception {

    log.info("/api/admin/category/delete : " + String.valueOf(seq));

    CategoryVO category = CategoryVO.builder()
            .seq(seq)
            .build();

    CmmnResponse response = categoryService.deleteCategory(category);

    return ResponseEntity.ok(response);
  }
}
