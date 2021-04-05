package com.fastcampus.java.model.entity;

import com.fastcampus.java.model.enumclass.OrderDetailStatus;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"orderGroup", "item"})
//@ToString(exclude = {"user", "item"}) // 상호 참조 해제
@Builder
@Accessors(chain = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderDetailStatus status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // 연관관계 설정 -> 반드시 객체이름으로 설정해야 함
    // OrderDetail : Item -> N : 1
    @ManyToOne
    private Item item;

    // OrderDetail : OrderGroup -> N : 1
    @ManyToOne
    private OrderGroup orderGroup;
}
