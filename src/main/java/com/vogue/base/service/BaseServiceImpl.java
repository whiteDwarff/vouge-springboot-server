//package com.vogue.base.service;
//
//
//import com.vogue.base.domain.CategoryVO;
//import com.vogue.base.mapper.CategoryMapper;
//import com.vogue.base.mapper.PermissionMapper;
//import com.vogue.common.CmmnResponse;
//import com.vogue.user.domain.UserVO;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BaseServiceImpl implements BaseService {
//
//  private final PermissionMapper permissionMapper;
//  private final CategoryMapper categoryMapper;
//
//  public BaseServiceImpl(PermissionMapper permissionMapper, CategoryMapper categoryMapper) {
//    this.permissionMapper = permissionMapper;
//    this.categoryMapper = categoryMapper;
//  }
//  @Override
//  public CmmnResponse getSystemMenu(String idntfCd) {
//
//    CmmnResponse response = new CmmnResponse();
//    response.put("permission", permissionMapper.getPermission());
//    response.put("category", categoryMapper.getCategory(idntfCd));
//
//    return response;
//  }
//}
