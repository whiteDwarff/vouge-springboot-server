package com.vogue.user;


import com.vogue.service.UserService;
import com.vogue.user.domain.SignInVO;
import com.vogue.user.domain.SignUpVO;
import com.vogue.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/*")

public class UserController {

  private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }
  @Autowired
  UserMapper mapper;

  @GetMapping("signIn")
  public  ResponseEntity<?> signIn(@RequestBody SignInVO vo) {
    log.info("POST : signIn" + vo.toString());
    return ResponseEntity.ok().build();
  }

  /*
   * 중복회원 검사 후 회원가입
   * @params : SignUpVo
   * @return : ResponseEntity
   * */
  @PostMapping("signUp")
  public ResponseEntity<?> signUp(@RequestBody SignUpVO vo) {
    log.info("POST : signUp" + vo.toString());
    if(userService.signUpUser(vo) == 0)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  /*
  * 이메일 중복검사
  * @params : SignUpVo
  * @return : ResponseEntity
  * */
  @PostMapping("hasEmail")
  public ResponseEntity<?> hasEmail(@RequestBody SignUpVO vo) {
    log.info("POST : hasEmail"  + vo.toString());
    if(userService.duplicateEmail(vo))
      return ResponseEntity.status(HttpStatus.CONFLICT).build();

    return ResponseEntity.ok().build();
  }
}
