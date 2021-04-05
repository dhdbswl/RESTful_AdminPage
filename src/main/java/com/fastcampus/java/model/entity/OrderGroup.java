package com.fastcampus.java.model.entity;

import com.fastcampus.java.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"user", "orderDetail"})
@Builder
@Accessors(chain = true)
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @Enumerated(EnumType.STRING)
    private OrderType orderType; // 주문의 형태 : 일괄 배송 or 개별 배송
    private String revAddress;
    private String revName;
    private String paymentType; // 결제 방식 : 카드 or 현금
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // OrderGroup : User -> N : 1
    @ManyToOne
    private User user;

    // OrderGroup : OderDetail -> 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
