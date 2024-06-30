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
   * @param     : HashMap
   * @Exception : throws Exception
   * @return    : ResponseEntity
   * 게시판 템플릿 등록, 수정 처리
   * */
  @PostMapping("saveNotice")
  public BaseResponse saveNotice(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /api/admin/posts/saveNotice : " + param.toString());

    return noticeService.saveNotice(param);
  }
  /**
   * 게시판의 템플릿, 공지사항, 말머리 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("get")
  public BaseResponse getPostsList(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("GET : /admin/posts/get : " + param.toString());

    return noticeService.getPostsList(param);
  }
  /**
   * @param     : HashMap
   * @Exception : throws Exception
   * @return    : ResponseEntity
   * 하나의 게시판 템플릿  조회
   * */
  @PostMapping
  public BaseResponse getSelectOne(@RequestBody HashMap<String, Object> param) throws Exception{

    log.info("POST : /admin/posts/selectOne : " + param.toString());

    return noticeService.selectOneNotice(param);
  }

  @PostMapping("delete")
  public BaseResponse deleteNotice(@RequestBody List<HashMap<String, Object>> param) throws Exception{

     log.info("POST : /api/admin/posts/delete : " + param.toString());

     return noticeService.deleteNotice(param);
  }
}
