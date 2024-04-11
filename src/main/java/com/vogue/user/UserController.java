package com.vogue.user;


import com.vogue.common.CmmnResponse;
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

  @PostMapping("signIn")
  public  ResponseEntity<?> signIn(@RequestBody SignInVO signIn) {
    log.info("POST : signIn" + signIn.toString());
    log.info(signIn.getEmail());
    log.info(signIn.getPassword());
    log.info(signIn.getToken());

    CmmnResponse response = userService.signInUser(signIn);
    HttpStatus httpStatus = response.getList().containsKey("user") ?
            UserStatus.OK.getCode() : UserStatus.UNAUTHORIZED.getCode();

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
    HttpStatus httpStatus = UserStatus.getStatusByMessage((String) response.get("message"));

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

}
