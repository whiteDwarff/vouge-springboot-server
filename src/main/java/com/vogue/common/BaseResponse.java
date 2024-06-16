package com.vogue.common;

import com.vogue.base.domain.BaseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
@ToString
public class BaseResponse {

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

  @Builder(builderClassName = "BaseCodeBuilder", builderMethodName = "BaseCodeBuilder")
  public BaseResponse(BaseCode code, HashMap<String, Object> list) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.list = list;
  }
}
