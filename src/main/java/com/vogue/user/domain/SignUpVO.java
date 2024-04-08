package com.vogue.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpVO {

  private String email;
  private String nickname;
  private String name;
  private String tel;
  private String password;

}
