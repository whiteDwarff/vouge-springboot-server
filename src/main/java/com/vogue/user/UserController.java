package com.vogue.user;


import com.vogue.user.domain.SignUpVO;
import com.vogue.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/*")

public class UserController {

  @Autowired
  UserMapper mapper;

  @GetMapping("login")
  public  ResponseEntity<?> signIn() {
    log.info("Get : login 요청 ");
    return ResponseEntity.ok().build();
  }
  @PostMapping("signUp")
  public ResponseEntity<?> signUp(@RequestBody SignUpVO vo) {
    log.info("Post : signUp 요청");
    log.info(vo.toString());

    int result = mapper.duplicateEmail(vo);
    log.info(String.valueOf(result));
    return ResponseEntity.ok().body(vo);
  }
}
