package com.fastcampus.java.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    // json body에 대해서 header 부분 공통으로 가져갈 때

    // api 통신시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;

    // data부
    private T data;

    // 정상적인 통신 -> ok
    public static <T> Header<T> OK() {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // data가 존재할 경우 data를 가지는 ok
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // 비정상적인 통신 -> error
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
