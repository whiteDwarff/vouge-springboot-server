package com.vogue.user.mapper;

import com.vogue.user.domain.EmailVO;
import com.vogue.user.domain.SignInVO;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {

  int duplicateEmail(SignUpVO vo);
  int duplicateUser(SignUpVO vo);
  void signUpUser(SignUpVO vo);
  int findByEmail(SignInVO vo);
  HashMap<String, Object> signInUser(SignInVO vo);
  String getEmail(SignUpVO vo);
  int getEmailCount(EmailVO vo);
  void issuanceTmpryPwd(EmailVO vo);

}
