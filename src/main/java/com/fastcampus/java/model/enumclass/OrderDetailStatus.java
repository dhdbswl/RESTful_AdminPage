package com.fastcampus.java.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderDetailStatus {
    ORDERING(0, "주문", "주문 상태"),
    CONFIRM(1, "확인", "주문 확인 상태");

    private Integer id;
    private String title;
    private String description;
}
