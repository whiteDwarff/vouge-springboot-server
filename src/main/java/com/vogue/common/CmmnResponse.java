package com.vogue.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Objects;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CmmnResponse {

  private String message;
  private HashMap<String, Object> list = new HashMap<>();

  public void put(String key, Object value) {
    list.put(key, value);
  }

}
