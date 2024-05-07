package com.vogue.admin.posts;


import com.vogue.admin.posts.domain.NoticeVO;
import com.vogue.admin.posts.service.NoticeService;
import com.vogue.code.AdminPostsStatus;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;
import com.vogue.common.DataSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/api/admin/posts/*")
@RestController
public class PostsController extends HashMap{

  private final NoticeService noticeService;

  public PostsController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  @PostMapping("insertNotice")
  public BaseResponse InsertNotice(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /api/admin/posts/insertNotice : " + param.toString());

    return noticeService.InsertNotice(param);

  }

  @PostMapping("get")
  public BaseResponse getPostsList(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("GET : /api/admin/posts/get : " + param.toString());

    return noticeService.getPostsList(param);
  }
}
