package com.vogue.admin.posts.service;

import com.vogue.admin.posts.mapper.NoticeMapper;
import com.vogue.code.AdminPostsStatus;
import com.vogue.common.BasePagination;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

  private final NoticeMapper noticeMapper;

  public NoticeServiceImpl(NoticeMapper noticeMapper) {
    this.noticeMapper = noticeMapper;
  }

  @Override
  public BaseResponse saveNotice(HashMap<String, Object> param) throws Exception {

    int result = 0;

    // 등록, form의 기본값에 seq가 없다.
    if(!Objects.nonNull(param.get("seq"))) {
      result = noticeMapper.insertNotice(param);
    // 수정, tablerow 클릭 시 form의 value엔 seq가 셋팅된다.
    } else {
      result = noticeMapper.updateNotice(param);
      // 말머리 삭제 후 재등록
      noticeMapper.deletePrepend(param);
    }

    // 빈값 확인용
    ArrayList<String> prepend = (ArrayList<String>) param.get("prepend");
    // 말머리 등록
    if(param.get("prependYn").equals("Y") && !prepend.isEmpty()) {
      noticeMapper.insertPrepend(param);
    }

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

    param.put("offset", page.getOffset());
    // 게시판 템플릿 리스트
    List<HashMap<String, Object>> noticeList = noticeMapper.selectNoticeList(param);
    map.put("notice", noticeList);

    return BaseResponse.builder()
            .status(HttpStatus.OK)
            .list(map)
            .build();
  }

  @Override
  public BaseResponse selectOneNotice(Long seq) throws Exception {

    HashMap<String, Object> map = new HashMap<>();
    HttpStatus status;

    if(Objects.nonNull(seq)) {
      // 템플릿 상세 데이터
      HashMap<String, Object> form = noticeMapper.selectOneNotice(seq);
      // 말머리
      List<String> prepend = noticeMapper.selectOnePrepend(seq);

      String prependYn = prepend.isEmpty() ? "N" : "Y";

      form.put("prependYn", prependYn);
      form.put("prepend", prepend);
      map.put("form", form);

      status = HttpStatus.OK;
    } else {
      map.put("message", "잘못된 접근방식 입니다.");
      status = HttpStatus.BAD_REQUEST;
    }

    return BaseResponse.builder()
            .status(status)
            .list(map)
            .build();
  }

  @Override
  public BaseResponse deleteNotice(List<HashMap<String, Object>> param) throws Exception {

    HttpStatus status = HttpStatus.OK;
    int result = 0;

    if(!param.isEmpty()) {
      for(HashMap<String, Object> notice : param) {
        noticeMapper.deleteNotice(notice);
        result ++;
      }
    }

    return BaseResponse.builder()
            .status(AdminPostsStatus.DELETE_OK.getCode())
            .message(String.valueOf(result) + AdminPostsStatus.DELETE_OK.getMessage())
            .build();
  }
}
