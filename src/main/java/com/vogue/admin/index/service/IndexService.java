package com.vogue.admin.index.service;

import com.vogue.common.BaseResponse;

import java.util.HashMap;

public interface IndexService {

    BaseResponse savePermission(HashMap<String, Object> param) throws Exception;
}
