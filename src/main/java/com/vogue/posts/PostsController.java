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
  @PostMapping("save")
  public BaseResponse savePosts(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /api/posts/save : " + param.toString());

    return postsService.savePosts(param);
  }

  @PostMapping("selectOne")
  public BaseResponse selectOne(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /api/posts/getPostsDetail" + param.toString());

    return postsService.selectOne(param);

  }
}
