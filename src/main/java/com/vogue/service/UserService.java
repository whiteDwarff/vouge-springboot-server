package com.vogue.service;


import com.vogue.user.domain.SignUpVO;

public interface UserService {

  boolean duplicateEmail(SignUpVO signUp);

  boolean duplicateUser(SignUpVO signUp);

  int signUpUser(SignUpVO signUp);

}
