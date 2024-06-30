package com.vogue.admin.posts;


import com.vogue.admin.posts.service.NoticeService;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/api/admin/posts/*")
@RestController
public class AdminPostsController{

  private final NoticeService noticeService;

  public AdminPostsController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  /**
   * 템플릿, 말머리 등록 및 수정
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("saveNotice")
  public BaseResponse saveNotice(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /api/admin/posts/saveNotice : " + param.toString());

    return noticeService.saveNotice(param);
  }
  /**
   * 게시판의 템플릿, 말머리 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("get")
  public BaseResponse getPostsList(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("GET : /admin/posts/get : " + param.toString());

    return noticeService.getPostsList(param);
  }
  /**
   * 게시판의 템플릿, 말머리 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping
  public BaseResponse selectOne(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/posts/selectOne : " + param.toString());

    return noticeService.selectOneNotice(param);
  }
  /**
   * 게시판의 템플릿, 말머리 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("delete")
  public BaseResponse deleteNotice(@RequestBody List<HashMap<String, Object>> param) throws Exception{

     log.info("POST : /api/admin/posts/delete : " + param.toString());

     return noticeService.deleteNotice(param);
  }
}
