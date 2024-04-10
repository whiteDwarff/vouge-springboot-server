package com.vogue.service;


import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.SignUpVO;

public interface UserService {

  CmmnResponse duplicateEmail(SignUpVO signUp);

  boolean duplicateUser(SignUpVO signUp);

  int signUpUser(SignUpVO signUp);

}
