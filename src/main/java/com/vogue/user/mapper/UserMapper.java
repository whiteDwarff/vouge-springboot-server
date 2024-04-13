package com.vogue.user.mapper;

import com.vogue.user.domain.SignInVO;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  int duplicateEmail(SignUpVO signUp);
  int duplicateUser(SignUpVO signUp);
  void signUpUser(SignUpVO signUp);
  int findByEmail(SignInVO signIn);
  UserVO signInUser(SignInVO signIn);
  String getEmail(SignUpVO signUp);


}
