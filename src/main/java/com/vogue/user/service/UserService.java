package com.vogue.user.service;


import com.vogue.common.BaseResponse;
import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.EmailVO;
import com.vogue.user.domain.SignInVO;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.domain.UserVO;

public interface UserService {

  CmmnResponse duplicateEmail(SignUpVO vo);

  CmmnResponse signUpUser(SignUpVO vo);

  BaseResponse signInUser(SignInVO vo);

  CmmnResponse findByEmail(SignUpVO vo);

  CmmnResponse findByPassword(EmailVO vo);

}
