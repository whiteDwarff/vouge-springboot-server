package com.vogue.common;

import com.vogue.base.domain.BaseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class BaseResponse extends HashMap<String, Object> {

  private final HttpStatus status;
  private final String message;


  @Builder
  public BaseResponse(HttpStatus status, String message, Object result) {
    this.status = status;
    this.message = message;
    this.put("result" , result);
  }

  @Builder(builderClassName = "BaseCodeBuilder", builderMethodName = "BaseCodeBuilder")
  public BaseResponse(BaseCode code, Object result) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.put("result" , result);
  }
}
