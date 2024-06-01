package com.vogue.user.service;

import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.EmailVO;
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

  private final MailService mailService;


  @Autowired
  public UserServiceImpl(UserMapper mapper, CaptchaService captchaService, MailService mailService) {
    this.mapper = mapper;
    this.captchaService = captchaService;
    this.mailService = mailService;
  }

  @Override
  public CmmnResponse signInUser(SignInVO signIn) {

    CmmnResponse response = new CmmnResponse();

    int result = mapper.findByEmail(signIn);
    String message = UserStatus.UNAUTHORIZED_EMAIL.getMessage();

    // 가입한 이메일이 있는 경우
    if(result > 0) {
      UserVO user = mapper.signInUser(signIn);
      Boolean captcha = captchaService.verifyToken(signIn.getToken());

      // 아이디와 비밀번호가 모두 일치한 경우
      if (Objects.nonNull(user)) {
          message = UserStatus.LOGIN_OK.getMessage();
          user.setPassword(null);
          response.put("user", user);
      }
      // 비밀번호가 일치하지 않은 경우
      else
        message = UserStatus.UNAUTHORIZED_PWD.getMessage();
      // google ReCaptcha 검증에 실패한 경우
      if (!captcha)
        message = UserStatus.UNAUTHORIZED_TOKEN.getMessage();
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

  @Override
  public CmmnResponse findByEmail(SignUpVO signUp) {

    CmmnResponse response = new CmmnResponse();

    String email = mapper.getEmail(signUp);

    if(Objects.isNull(email)) response.setMessage(UserStatus.UNAUTHORIZED_EMAIL.getMessage());
    else response.put("email", email);

    return response;
  }

  @Override
  public CmmnResponse findByPassword(EmailVO vo) {

    CmmnResponse response = new CmmnResponse();

    int result = mapper.getEmailCount(vo);
    String message = UserStatus.UNAUTHORIZED_EMAIL.getMessage();

    if(result > 0) {
      // 사용자의 이메일로 메일 발송
      mailService.sendMail(vo);
      // 사용자의 비밀번호를 임시 비밀번호로 변경
      mapper.issuanceTmpryPwd(vo);
      message = UserStatus.TEMPORARY_PWD.getMessage();
    }
    response.setMessage(message);
    response.put("result", result);
    return response;
  }

}
