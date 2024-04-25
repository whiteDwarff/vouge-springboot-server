package com.vogue.base.service;

import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.UserVO;

public interface BaseService {

  CmmnResponse getSystemMenu(String idntfCd);
}
