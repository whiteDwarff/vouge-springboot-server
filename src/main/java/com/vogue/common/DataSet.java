package com.vogue.common;

import lombok.*;

import java.util.HashMap;


@Getter
@ToString
@NoArgsConstructor
public class DataSet extends HashMap<String, Object> {
  private String message;
  private int min;           // start page
  private int max;           // end page
  private int current;       // current page
  private int maxPage = 5;   // max page

  private final HashMap<String, Object> list = new HashMap<>();

  public void add(String key, Object value) {
    list.put(key, value);
  }
  @Builder
  public DataSet(String message,
                      int min,
                      int max,
                      int current,
                      HashMap<String, Object> list) {
    this.message = message;
    this.min = min;
    this.max = max;
    this.current = current;
  }


}
