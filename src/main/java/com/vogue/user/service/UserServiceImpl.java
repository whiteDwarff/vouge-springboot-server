package com.vogue.user.service;

import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.SignInVO;
import com.vogue.user.domain.SignUpVO;
import com.vogue.code.UserStatus;
import com.vogue.user.domain.UserVO;
import com.vogue.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService{


  private final UserMapper mapper;
  private final CaptchaService captchaService;


  @Autowired
  public UserServiceImpl(UserMapper mapper, CaptchaService captchaService) {
    this.mapper = mapper;
    this.captchaService = captchaService;
  }

  @Override
  public CmmnResponse signInUser(SignInVO signIn) {

    CmmnResponse response = new CmmnResponse();

    UserVO user = mapper.signInUser(signIn);
    String message = UserStatus.UNAUTHORIZED.getMessage();

    Boolean captcha = captchaService.verifyToken(signIn.getToken());

    if(!captcha)
      message = UserStatus.UNAUTHORIZED_TOKEN.getMessage();

    if(Objects.nonNull(user)) {
      message = UserStatus.OK.getMessage();
      response.put("user", user);
    }
    response.setMessage(message);

    return response;
  }

  @Override
  public CmmnResponse duplicateEmail(SignUpVO signUp) {

    CmmnResponse response = new CmmnResponse();

    int result = mapper.duplicateEmail(signUp);
    String message = UserStatus.HAS_EMAIL_SUCCESS.getMessage();

    if(result > 0)
      message = UserStatus.CONFLICT_EMAIL.getMessage();

    response.put("result", result);
    response.setMessage(message);

    return response;
  }

  @Override
  public CmmnResponse signUpUser(SignUpVO signUp) {

    CmmnResponse response = new CmmnResponse();

    int result = mapper.duplicateUser(signUp);
    Boolean captcha = captchaService.verifyToken(signUp.getToken());
    String message = UserStatus.CREATED.getMessage();

    if(result > 0)
      message = UserStatus.CONFLICT_USER.getMessage();

    if(!captcha)
      message = UserStatus.UNAUTHORIZED_TOKEN.getMessage();

    if(result == 0 && captcha)
      mapper.signUpUser(signUp);

    response.setMessage(message);

    return response;
  }

}
