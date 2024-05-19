package com.vogue.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Getter
@ToString
@NoArgsConstructor

@Slf4j
public class BasePagination {

  private int min;

  private int max;

  private int current;

  private int offset;

  private final int maxPages = 10;

  @Builder
  public BasePagination(int min, int max, int current, int offset) {
    this.min = min;
    this.max = max;
    this.current = current;
    this.offset = offset;
  }

  public HashMap<String, Object> getPagination() {
    HashMap<String, Object> map = new HashMap<>();

    map.put("min", min);
    map.put("max", max);
    map.put("current", current);
    map.put("maxPages", maxPages);
    map.put("offset", offset);

    return map;
  }

  public HashMap<String, Object> setPagination(int count, int currentPage) {

    if(count > 0) {
      min = 1;
      max = (int) Math.ceil((double) count / maxPages);
      current = currentPage;
      offset = (current * maxPages) - maxPages;
    } else {
      // 불러올 데이터가 없으면 초기값 셋팅
      min = 1;
      max = 1;
      current = 1;
      offset = count;
    }
    return this.getPagination();
  }
}
