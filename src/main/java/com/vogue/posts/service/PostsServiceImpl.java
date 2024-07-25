package com.vogue.posts.service;


import com.vogue.common.BasePagination;
import com.vogue.common.BaseResponse;
import com.vogue.posts.mapper.PostsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Service
public class PostsServiceImpl implements PostsService{

  private final PostsMapper postsMapper;

  public PostsServiceImpl(PostsMapper postsMapper) {
    this.postsMapper = postsMapper;
  }

  @Value("${page.list.max-page.10}")
  private int maxPages;

  /**
   * 게시글 등록 및 수정
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse save(HashMap<String, Object> param) throws Exception {

    HttpStatus status = HttpStatus.OK;

    try {
      // 게시글 등록
      if(param.get("seq").toString().isEmpty()) {
        postsMapper.insertPosts(param);
      // 게시글 수정
      } else {
        postsMapper.update(param);
      }
    } catch (Exception e) {
      e.printStackTrace();
      status = HttpStatus.BAD_REQUEST;
    }

    return BaseResponse.BaseCodeBuilder()
              .status(status)
              .result(param)
              .build();
  }
  /**
   * 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse selectOne(HashMap<String, Object> param) throws Exception {

    HashMap<String, Object> result = postsMapper.selectOne(param);
    HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

    return BaseResponse.BaseCodeBuilder()
            .status(status)
            .result(result)
            .build();
  }
  /**
   * 게시글 수정 > 게시글 상세 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse selectEditInfo(HashMap<String, Object> param) throws Exception {

    HashMap<String, Object> result = postsMapper.selectEditInfo(param);
    HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

    return BaseResponse.BaseCodeBuilder()
            .status(status)
            .result(result)
            .build();
  }
  /**
   * 게시글 목록 조회
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse selectByPaging(HashMap<String, Object> param) throws Exception {

    try {
      if(Objects.nonNull(param.get("category"))) {
        int count = postsMapper.selectByPagingCount(param);

        param.put("maxPages", maxPages);
        param.put("page", new BasePagination().setPagination(count, (int) param.get("current"), param));
        param.put("list", postsMapper.selectByPaging(param));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return BaseResponse.BaseCodeBuilder()
            .result(param)
            .build();
  }
  /**
   * 게시글 삭제
   * @params HashMap
   * */
  public BaseResponse delete(HashMap<String, Object> param) throws Exception {

    HttpStatus status = HttpStatus.OK;

    if(param.get("postSeq") != null) {
      postsMapper.delete(param);
    } else {
      status = HttpStatus.BAD_REQUEST;
    }
    return BaseResponse.BaseCodeBuilder()
            .status(status)
            .build();
  }
}
