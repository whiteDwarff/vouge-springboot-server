package com.vogue.base.controller;


import com.vogue.base.domain.CategoryVO;
import com.vogue.base.service.CategoryService;
import com.vogue.code.CategoryStatus;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/ctgry/*")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping("/insert")
  public ResponseEntity<?> insertCategory(@RequestBody CategoryVO vo) {

    log.info("/api/ctgry/insert : " + vo.toString());
    CmmnResponse response = categoryService.InsertCategory(vo);
    int result = (int) response.get("result");

    HttpStatus httpStatus = result > 0 ?
            CategoryStatus.CONFLICT_NAME.getCode() : CategoryStatus.CREATED.getCode();

    return ResponseEntity.status(httpStatus).body(response);




  }
}
