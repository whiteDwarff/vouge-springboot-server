package com.vogue.user.service;

import com.vogue.user.domain.EmailVO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;
@Slf4j
@Service
public class MailService {
  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;

  public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  /*
  * 임시 비밀번호 생성
  * */
  public String temporaryPassword() {
    Random random = new Random();
    StringBuffer key = new StringBuffer();

    for (int i = 0; i < 8; i++) {
      int index = random.nextInt(4);

      switch (index) {
        case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
        case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
        default: key.append(random.nextInt(9));
      }
    }
    // login form에 특수문자가 하나 이상 필요
    return key.toString() + "!";
  }

  public void sendMail(EmailVO vo) {
    String newPassword = temporaryPassword();
    vo.setPassword(newPassword);

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      mimeMessageHelper.setTo(vo.getEmail()); // 메일 수신자
      mimeMessageHelper.setSubject("[VOUGE] 임시 비밀번호 발급"); // 메일 제목
      mimeMessageHelper.setText(setContext(newPassword), true); // 메일 본문 내용, HTML 여부
      javaMailSender.send(mimeMessage);

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  // thymeleaf를 통한 html 적용
  public String setContext(String code) {
    Context context = new Context();
    context.setVariable("code", code);
    return templateEngine.process("mail", context);
  }
}
