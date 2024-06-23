package com.vogue.posts.service;


import com.vogue.base.domain.BaseCode;
import com.vogue.common.BaseResponse;
import com.vogue.posts.mapper.PostsMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class PostsServiceImpl implements PostsService{

  private final PostsMapper postsMapper;

  public PostsServiceImpl(PostsMapper postsMapper) {
    this.postsMapper = postsMapper;
  }

  /**
   * @params HashMap
   * @return BaseResponse
   * */
  @Override
  public BaseResponse savePosts(HashMap<String, Object> param) throws Exception {

    String seq = (String) param.get("seq");
    HttpStatus status = null;

    try {
      // 게시글 등록
      if(Objects.nonNull(seq)) {
        postsMapper.insertPosts(param);
      } else {

      }
    } catch (Exception e) {
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
    return BaseResponse.builder()
            .result(postsMapper.selectOne(param))
            .build();
  }
}
