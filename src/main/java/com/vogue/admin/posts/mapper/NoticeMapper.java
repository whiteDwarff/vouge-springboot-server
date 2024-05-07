package com.vogue.admin.posts.mapper;

import com.vogue.admin.posts.domain.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NoticeMapper {

  int insertNotice(HashMap<String, Object> param) throws Exception;

  int selectNoticeCount(HashMap<String, Object> param) throws Exception;

  List<HashMap<String, Object>> selectNoticeList(HashMap<String, Object> param) throws Exception;
}
