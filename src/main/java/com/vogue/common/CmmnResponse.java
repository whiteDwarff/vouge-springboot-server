package com.vogue.common;

import lombok.*;

import java.util.HashMap;
import java.util.Objects;

@Data
@ToString
@NoArgsConstructor
public class CmmnResponse {

  private String message;
  private HashMap<String, Object> list = new HashMap<>();

  public void put(String key, Object value) {
    list.put(key, value);
  }
  public Object get(String key) {
    return list.get(key);
  }

  @Builder
  public CmmnResponse(String message,  HashMap<String, Object> list) {
    this.message = message;
    this.list = list;
  }
}
