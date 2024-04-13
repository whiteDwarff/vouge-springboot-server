package com.vogue.user.service;


import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.SignInVO;
import com.vogue.user.domain.SignUpVO;

public interface UserService {

  CmmnResponse duplicateEmail(SignUpVO signUp);

  CmmnResponse signUpUser(SignUpVO signUp);

  CmmnResponse signInUser(SignInVO signIn);

  CmmnResponse findByEmail(SignUpVO signUp);

}
