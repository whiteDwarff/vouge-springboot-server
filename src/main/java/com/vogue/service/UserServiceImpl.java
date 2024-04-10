package com.vogue.service;

import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.domain.UserStatus;
import com.vogue.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{


  private final UserMapper mapper;


  @Autowired
  public UserServiceImpl(UserMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public CmmnResponse duplicateEmail(SignUpVO signUp) {

    CmmnResponse response = new CmmnResponse();

    if(mapper.duplicateEmail(signUp) > 0)
      response.put("message", UserStatus.CONFLICT_EMAIL.getMessage());

    response.put("message", UserStatus.HAS_EMAIL_SUCCESS.getMessage());

    return response;
  }

  @Override
  public boolean duplicateUser(SignUpVO signUp) {
    return mapper.duplicateUser(signUp) > 0;
  }

  @Override
  public int signUpUser(SignUpVO signUp) {
    int result = mapper.duplicateUser(signUp);
    return result == 0 ?  mapper.signUpUser(signUp) : 0;

  }
}
