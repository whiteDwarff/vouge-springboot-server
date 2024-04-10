package com.vogue.user;


import com.vogue.service.CaptchaService;
import com.vogue.service.UserService;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.domain.UserStatus;
import com.vogue.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/*")

public class UserController {

  private final UserService userService;
  private final CaptchaService captchaService;

  public UserController(UserService userService, CaptchaService captchaService){
    this.userService = userService;
    this.captchaService = captchaService;
  }

  @GetMapping("signIn")
  public  ResponseEntity<?> signIn(@RequestParam String token) {
    log.info("GET : signIn" + token);

    return ResponseEntity.ok(captchaService.verifyToken(token));
  }

  /*
   * 중복회원 검사 후 회원가입
   * @params : SignUpVo
   * @return : ResponseEntity
   * */
  @PostMapping("signUp")
  public ResponseEntity<?> signUp(@RequestBody SignUpVO vo) {
    log.info("POST : signUp" + vo.toString());

    // 이름과 휴대폰 번호로 가입된 정보가 있는 경우
    if(userService.signUpUser(vo) == 0)
      return ResponseEntity
              .status(UserStatus.CONFLICT_USER.getCode())
              .body(UserStatus.CONFLICT_USER.getMessage());
    // Google ReCaptcha의 score가 0.5 이하
    if(!captchaService.verifyToken(vo.getToken()))
      return ResponseEntity
              .status(UserStatus.UNAUTHORIZED_TOKEN.getCode())
              .body(UserStatus.UNAUTHORIZED_TOKEN.getMessage());

    return ResponseEntity
            .status(UserStatus.CREATED.getCode())
            .body(UserStatus.CREATED.getMessage());
  }
  /*
  * 이메일 중복검사
  * @params : SignUpVo
  * @return : ResponseEntity
  * */
  @PostMapping("hasEmail")
  public ResponseEntity<?> hasEmail(@RequestBody SignUpVO vo) {
    log.info("POST : hasEmail =>"  + vo.toString());

    // 해당 이메일로 가입된 정보가 있는 경우
//    if(userService.duplicateEmail(vo))
//      return ResponseEntity
//              .status(UserStatus.CONFLICT_EMAIL.getCode())
//              .body(UserStatus.CONFLICT_EMAIL.getMessage());
//
//    return ResponseEntity
//            .ok()
//            .body(UserStatus.HAS_EMAIL_SUCCESS.getMessage());
  }

}
