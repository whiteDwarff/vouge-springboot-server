package com.vogue.common;

import com.vogue.base.domain.BaseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@ToString
public class BaseResponse extends HashMap<String, Object> {

  @Builder
  public BaseResponse(HttpStatus status, String message, Map<String, Object> result) {
    this.put("status", status);
    this.put("message", message);
    if (result != null) this.putAll(result);
  }

  @Builder(builderClassName = "BaseCodeBuilder", builderMethodName = "BaseCodeBuilder")
  public BaseResponse(HttpStatus status, Map<String, Object> result) {
    HttpStatus newStatus = Objects.nonNull(status) ? status : HttpStatus.OK;
    if (result != null) this.putAll(result);
    this.put("status", newStatus);
  }

  public void setResponseStatus(BaseCode code) {
    if (code != null) {
      HashMap<String, Object> map = new HashMap<>();
      map.put("code", code.getStatus());
      map.put("message", code.getMessage());
      this.put("status", map);
    }
  }

}
