package com.vogue.base.controller;

import com.vogue.base.service.BaseService;
import com.vogue.common.BaseFileUtil;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/system/*")
public class BaseController {

  private final BaseService baseService;

  private final BaseFileUtil baseFileUtil;

  public BaseController(BaseService baseService, BaseFileUtil baseFileUtil) {
    this.baseService = baseService;
    this.baseFileUtil = baseFileUtil;

  }

  @PostMapping("get")
  public BaseResponse getSystemMenu(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : api/system/getSystemMenu : " + param.toString());

    return baseService.getSystemMenu(param);
  }

  @PostMapping("imageUpload")
  public List<HashMap<String, Object>> saveImage(@RequestParam("images") MultipartFile[] images, @RequestParam("dir") String dir) throws Exception {

    log.info("POST : api/system/imageUpload : " );

    return baseFileUtil.fileUploadUtil(images, File.separator + dir);

  }

}
