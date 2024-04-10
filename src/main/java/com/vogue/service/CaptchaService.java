package com.vogue.service;


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

@Slf4j
@Service
public class CaptchaService {

  @Value("${recaptcha.verify_url}")
  private String url;
  @Value("${recaptcha.secret_key}")
  private String key;
  @Autowired
  RestTemplateBuilder builder;

  public Boolean verifyToken(String token) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    map.add("secret", key);
    map.add("response", token);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    ResponseEntity<String> response = builder.build().postForEntity( url, request , String.class );

    JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();


    log.info(" #### score : " + String.valueOf(jsonObject.get("score")));
    return Double.parseDouble(String.valueOf(jsonObject.get("score"))) >= 0.5;
  }
}
