package com.vogue.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@Getter
@ToString
public class BaseResponse {

 // private ResponseEntity<?> response;

  private final HttpStatus status;

  private final HashMap<String, Object> list;

  private final String message;

  public void put(String key, Object value) {
    this.list.put(key, value);
  }

  public Object getKey(String key) {
    return key.isEmpty() ?
            null : this.list.get(key);
  }

  public HashMap<String, Object> getList() {
    return list == null ?
            new HashMap<>() : list;
  }

  @Builder
  public BaseResponse(HttpStatus status, String message, HashMap<String, Object> list) {
    this.status = status;
    this.message = message;
    this.list = list;
  }

//  public ResponseEntity<?> getResponse() {
//    response = ResponseEntity.status(status).body(list);
//    return response;
//  }
}
