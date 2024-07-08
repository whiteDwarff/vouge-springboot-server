package com.vogue.posts;

import com.vogue.common.BaseFileUtil;
import com.vogue.common.BaseResponse;
import com.vogue.posts.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("api/posts/*")
@RestController
public class PostsController {


  private final PostsService postsService;

  private final BaseFileUtil baseFileUtil;

  public PostsController(PostsService postsService, BaseFileUtil baseFileUtil) {
    this.postsService = postsService;
    this.baseFileUtil = baseFileUtil;
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
  /**
   * 게시글 이미지 업로드
   * @params MultipartFile
   * @return BaseResponse
   */
//  @PostMapping("/images")
//  public CompletableFuture<List<HashMap<String, Object>>> addPostImages(@RequestParam("images") MultipartFile[] images) throws Exception {
//    log.info("POST : /posts/images");
//
//    return baseFileUtil.qqq(images, "/posts");
//  }

  @PostMapping("/images")
  public List<HashMap<String, Object>> addPostImages(@RequestParam("images") MultipartFile[] images) throws Exception {
    log.info("POST : /posts/images");

    List<HashMap<String, Object>> result = baseFileUtil.fileUploadUtil(images, "/posts");

    for(HashMap<String, Object> map : result) {
      log.info("@@@ MAP : " + map.toString());
    }
    return result;
  }

}
