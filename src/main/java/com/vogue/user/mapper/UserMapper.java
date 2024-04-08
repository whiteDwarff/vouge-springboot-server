package com.vogue.user.mapper;

import com.vogue.user.domain.SignUpVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  int duplicateEmail(SignUpVO signUp);
  int duplicateUser(SignUpVO signUp);
  int signUpUser(SignUpVO signUp);


}
