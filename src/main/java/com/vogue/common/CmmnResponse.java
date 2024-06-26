package com.vogue.common;

import lombok.*;

import java.util.HashMap;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class CmmnResponse {

  // response message
  private String message;
  private int min;           // start page
  private int max;           // end page
  private int current;       // current page
  private int maxPage = 5;   // max page

  private HashMap<String, Object> list = new HashMap<>();

  public void put(String key, Object value) {
    this.list.put(key, value);
  }

  public void setMessage(String message) {
    this.message = message;
  }
  public void setResult(Object value) {
    this.list.put("result", value);
  }
  public Object get(String key) {
    return list.get(key);
  }

  @Builder
  public CmmnResponse(String message,
                      int min,
                      int max,
                      int current,
                      HashMap<String, Object> list) {
    this.message = message;
    this.min = min;
    this.max = max;
    this.current = current;
    this.list = list;
  }
}
