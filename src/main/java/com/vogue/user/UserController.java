package com.vogue.user;


import com.vogue.common.CmmnResponse;
import com.vogue.user.domain.EmailVO;
import com.vogue.user.domain.SignInVO;
import com.vogue.user.service.UserService;
import com.vogue.user.domain.SignUpVO;
import com.vogue.code.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/*")

public class UserController {

  private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }
  /*
   * 로그인
   * @params : SignInVO
   * @return : ResponseEntity
   * */
  @PostMapping("signIn")
  public ResponseEntity<?> signIn(@RequestBody SignInVO vo) {
    log.info("POST : signIn" + vo.toString());

    CmmnResponse response = userService.signInUser(vo);
    log.info("message : " +  (String) response.getMessage());
    HttpStatus httpStatus = UserStatus.getStatusByMessage((String) response.getMessage());

    return ResponseEntity.status(httpStatus).body(response);
  }

  /*
   * 중복회원 검사 후 회원가입
   * @params : SignUpVo
   * @return : ResponseEntity
   * */
  @PostMapping("signUp")
  public ResponseEntity<?> signUp(@RequestBody SignUpVO vo) {
    log.info("POST : signUp" + vo.toString());


    CmmnResponse response = userService.signUpUser(vo);
    HttpStatus httpStatus = UserStatus.getStatusByMessage((String) response.getMessage());

    return ResponseEntity.status(httpStatus).body(response);
  }

  /*
  * 이메일 중복검사
  * @params : SignUpVo
  * @return : ResponseEntity
  * */
  @PostMapping("hasEmail")
  public ResponseEntity<?> hasEmail(@RequestBody SignUpVO vo) {
    log.info("POST : hasEmail =>"  + vo.toString());

    CmmnResponse response = userService.duplicateEmail(vo);
    int result = (int) response.get("result");

    HttpStatus httpStatus;

    httpStatus = result > 0 ?
            UserStatus.CONFLICT_EMAIL.getCode() : UserStatus.HAS_EMAIL_SUCCESS.getCode();

    return ResponseEntity.status(httpStatus).body(response);
  }
  /*
   * 이메일 찾기
   * @params : SignUpVo
   * @return : ResponseEntity
   * */
  @PostMapping("email")
  public ResponseEntity<?> findByEmail(@RequestBody SignUpVO vo) {
    log.info("POST : email =>"  + vo.toString());

    CmmnResponse response = userService.findByEmail(vo);

    HttpStatus status = response.getList().containsKey("email") ?
            UserStatus.LOGIN_OK.getCode() : UserStatus.UNAUTHORIZED_EMAIL.getCode();

    return ResponseEntity.status(status).body(response);
  }

  /*
   * 비밀번호 찾기
   * @params : EmailVO
   * @return : ResponseEntity
   * */
  @PostMapping("password")
  public ResponseEntity<?> findByPassword(@RequestBody EmailVO vo) {
    log.info("POST : password =>"  + vo.toString());

    CmmnResponse response = userService.findByPassword(vo);

    int result = (int) response.get("result");
    HttpStatus status = result > 0 ?
            UserStatus.TEMPORARY_PWD.getCode() : UserStatus.UNAUTHORIZED_EMAIL.getCode();

    return ResponseEntity.status(status).body(response);
  }

}
