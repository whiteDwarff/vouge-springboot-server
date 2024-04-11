package com.vogue.user.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class CaptchaService {

  @Value("${recaptcha.verify_url}")
  private String url;
  @Value("${recaptcha.secret_key}")
  private String key;

  private final RestTemplate restTemplate;

  // RestTemplateBuilder의 build()를 사용하여 RestTemplate을 생성
  public CaptchaService (RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  public Boolean verifyToken(String token) {

    // 요청 헤더 정의
    HttpHeaders headers = new HttpHeaders();
    // 헤더의 contentType을 Form 방식으로 지정
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    map.add("secret", key);
    map.add("response", token);


    // 요청에 대한 응답 정의
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    // Request 요청
    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );

    JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();

    log.info(token);
    log.info(response.getBody());
    return true;
//    log.info(" #### score : " + String.valueOf(jsonObject.get("score")));
//    return Double.parseDouble(String.valueOf(jsonObject.get("score"))) >= 0.5;
    }
}
