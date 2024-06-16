package com.vogue.posts.service;


import com.vogue.base.domain.BaseCode;
import com.vogue.common.BaseResponse;
import com.vogue.posts.mapper.PostsMapper;
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

    String seq = (String) param.get("seq"), str = "등록";
    boolean isSaveState = true;

    // 게시글 등록
    if(Objects.nonNull(seq)) {
      try {
        postsMapper.insertPosts(param);
      } catch (Exception e) {
        e.printStackTrace();
        isSaveState = false;
      }
    // 게시글 수정
    } else {
      str = "수정";
    }
    BaseCode code = isSaveState ? BaseCode.getOK(str) : BaseCode.getERROR(str);

    return BaseResponse.BaseCodeBuilder()
              .code(code)
              .list(param)
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
            .list(postsMapper.selectOne(param))
            .build();
  }
}
