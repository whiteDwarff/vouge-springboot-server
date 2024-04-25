//package com.vogue.base.controller;
//
//import com.vogue.base.service.BaseService;
//import com.vogue.common.CmmnResponse;
//import com.vogue.user.domain.UserVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/system/*")
//public class BaseController {
//
//  private final BaseService baseService;
//
//  public BaseController(BaseService baseService) {
//    this.baseService = baseService;
//  }
//
//  @GetMapping("getSystemAll")
//  public ResponseEntity<?> getBaseList(@RequestParam("idntfCd") String idntfCd) {
//
//    log.info("api/system/getSystemAll : " + idntfCd);
//
//
//    CmmnResponse response = baseService.getSystemMenu(idntfCd);
//    return ResponseEntity.ok().body(response);
//  }
//
//}
