package com.vogue.posts;

import com.vogue.common.BaseResponse;
import com.vogue.posts.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Slf4j
@RequestMapping("api/posts/*")
@RestController
public class PostsController {


  private final PostsService postsService;

  public PostsController(PostsService postsService) {
    this.postsService = postsService;
  }

  /**
   * 게시들 등록 및 수정
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("save")
  public BaseResponse save(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /api/posts/save : " + param.toString());

    return postsService.save(param);
  }
  /**
   * 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("selectOne")
  public BaseResponse selectOne(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /api/posts/selectOne" + param.toString());

    return postsService.selectOne(param);
  }
  /**
   * 게시글 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("selectByPaging")
  public BaseResponse selectByPaging(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /api/posts/selectByPaging" + param.toString());

    return postsService.selectByPaging(param);
  }
}
