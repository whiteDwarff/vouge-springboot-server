package com.vogue.service;

import com.vogue.user.domain.SignUpVO;
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
  public boolean duplicateEmail(SignUpVO signUp) {
    return mapper.duplicateEmail(signUp) > 0;
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
