package com.fastcampus.java.model.entity;

import com.fastcampus.java.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.time.LocalDateTime;
import java.util.List;

@Data // 객체 사용
@AllArgsConstructor // 모든 매개변수를 가지는 생성자
@NoArgsConstructor // 기본 생성자
@Entity // == db table과 같다.
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"orderGroup"})
@Builder
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 어떤 식으로 관리할 것인지에 대해서 전략을 설정.
    // mysql을 사용할 것이기 때문에 IDENTITY 타입으로 설정
    private Long id;
    private String account;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // User : OrderGroup -> 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

    // FetchType 1. LAZY, 2. EAGER

    // 1. LAZY : 지연 로딩. 한 가지의 테이블에 대해서만 쿼리가 실행됨. select * from item where id = ?
    // 변수에 대해서 get메소드를 호출하지 않는 이상 연관관계가 설정된 테이블에 대해서 select를 하지 않겠다는 의미

    // 2. EAGER : 즉시 로딩. 모든 JOIN형태의 쿼리문으로 쿼리가 실행됨.
    // 연관관계가 설정된 모든 테이블에 대해서 JOIN이 실행됨을 의미
    // 데이터가 많은 테이블에 대해서는 성능 저하가 일어날 수 있음. 1 : 1 연관에 추천.
}
