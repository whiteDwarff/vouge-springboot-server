package com.vogue.base;

import com.vogue.base.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system/*")
public class BaseController {

  private final BaseService baseService;

  public BaseController(BaseService baseService) {
    this.baseService = baseService;
  }

  @GetMapping("/getSystemAll")
  public ResponseEntity<?> getBaseList() {
    return ResponseEntity.ok().body(baseService.getSystemMenu());
  }

}
