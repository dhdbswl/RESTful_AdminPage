package com.fastcampus.java.model.network.request;

import com.fastcampus.java.model.enumclass.OrderDetailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailApiRequest {
    private Long id;
    private OrderDetailStatus status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long itemId;
    private Long orderGroupId;
}
