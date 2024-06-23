package com.vogue.base.controller;

import com.vogue.base.service.BaseService;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/system/*")
public class BaseController {

  private final BaseService baseService;

  public BaseController(BaseService baseService) {
    this.baseService = baseService;
  }

  @PostMapping("get")
  public BaseResponse getSystemMenu(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : api/system/getSystemMenu : " + param.toString());

    return baseService.getSystemMenu(param);
  }
}
