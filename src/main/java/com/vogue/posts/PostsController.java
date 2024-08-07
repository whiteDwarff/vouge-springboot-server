package com.vogue.posts;

import com.vogue.common.BaseFileUtil;
import com.vogue.common.BaseResponse;
import com.vogue.posts.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("api/posts/*")
@RestController
public class PostsController {

  private final PostsService postsService;

  private final BaseFileUtil baseFileUtil;

  private final Logger logger = LoggerFactory.getLogger(PostsController.class);

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
   * 게시글 수정 > 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("edit")
  public BaseResponse selectEditInfo(@RequestBody HashMap<String, Object> param) throws Exception {

    log.info("POST : /api/posts/edit" + param.toString());

    return postsService.selectEditInfo(param);
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
  @PostMapping("/images")
  public List<HashMap<String, Object>> addPostImages(@RequestParam("images") MultipartFile[] images) throws Exception {
    log.info("POST : /posts/images");

    return baseFileUtil.fileUploadUtil(images, "/posts");
  }
 /**
   * 게시글 삭제
   * @params HashMap
   * @return BaseResponse
   */
  @PostMapping("/delete")
  public BaseResponse delete(@RequestBody HashMap<String, Object> param) throws Exception {
    log.info("POST : /posts/delete : " + param.toString());

    return postsService.delete(param);
  }
  /**
   * 게시글 좋아요 등록 및 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("/toggleLiked")
  public BaseResponse toggleLiked(@RequestBody HashMap<String, Object> param) throws Exception {
    log.info("POST : /posts/toggleLiked : " + param.toString());

    return postsService.toggleLiked(param);
  }
  /**
   * 게시글 좋아요 등록 및 삭제
   * @params HashMap
   * @return BaseResponse
   * */
  @PostMapping("/addComment")
  public BaseResponse addComment(@RequestBody HashMap<String, Object> param) throws Exception {
    log.info("POST : /posts/addComment : " + param.toString());

    return postsService.addComment(param);
  }
}
