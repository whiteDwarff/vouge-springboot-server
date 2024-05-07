package com.vogue.admin.posts.service;

import com.vogue.admin.posts.domain.NoticeVO;
import com.vogue.admin.posts.mapper.NoticeMapper;
import com.vogue.code.AdminPostsStatus;
import com.vogue.common.BasePagination;
import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

  private final NoticeMapper noticeMapper;

  public NoticeServiceImpl(NoticeMapper noticeMapper) {
    this.noticeMapper = noticeMapper;
  }

  @Override
  public BaseResponse InsertNotice(HashMap<String, Object> param) throws Exception {

    int result = noticeMapper.insertNotice(param);

    AdminPostsStatus state =
      result == 1 ? AdminPostsStatus.NOTICE_CREATED : AdminPostsStatus.INTERNAL_SERVER_ERROR;

    return BaseResponse.builder()
            .status(state.getCode())
            .message(state.getMessage())
            .build();
  }

  @Override
  public BaseResponse getPostsList(HashMap<String, Object> param) throws Exception {

    // 게시판 템플릿 개수
    int count = noticeMapper.selectNoticeCount(param);
    HashMap<String, Object> map = new HashMap<>();

      // 페이지 정보
      BasePagination page = new BasePagination();

      map.put("page", page.setPagination(count, (int) param.get("current")));


      log.info(page.toString());
      param.put("offset", page.getOffset());
      // 게시판 템플릿 리스트
      List<HashMap<String, Object>> noticeList = noticeMapper.selectNoticeList(param);
      map.put("notice", noticeList);

    return BaseResponse.builder()
            .status(HttpStatus.OK)
            .list(map)
            .build();
  }
}
