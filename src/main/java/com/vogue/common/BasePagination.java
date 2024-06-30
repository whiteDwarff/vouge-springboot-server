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

    map.put("min", this.min);
    map.put("max", this.max);
    map.put("current", this.current);
    map.put("maxPages", this.maxPages);
    map.put("offset", this.offset);

    return map;
  }

  public HashMap<String, Object> setPagination(int count, int currentPage) {

    if(count > 0) {
      this.min = 1;
      this.max = (int) Math.ceil((double) count / maxPages);
      this.current = currentPage;
      this.offset = (current * maxPages) - maxPages;
    } else {
      // 불러올 데이터가 없으면 초기값 셋팅
      this.min = 1;
      this.max = 1;
      this.current = 1;
      this.offset = count;
    }
    return this.getPagination();
  }
}
