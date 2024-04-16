package com.vogue.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

  private int seq;
  private String email;
  private String password;
  private String name;
  private String nickname;
  private String tel;
  private String photoURL;
  private String idntfCd;
  private Date createdAt;

}
