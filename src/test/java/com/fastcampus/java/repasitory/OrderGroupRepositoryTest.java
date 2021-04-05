package com.fastcampus.java.repasitory;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.OrderGroup;
import com.fastcampus.java.model.enumclass.OrderType;
import com.fastcampus.java.repository.OrderDetailRepository;
import com.fastcampus.java.repository.OrderGroupRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends JavaApplicationTests {
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void created() {
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType(OrderType.ALL);
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("홍길동");
        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now());
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
        //orderGroup.setUserId(1L); // -> jpa 연관관계에 의해 user로 바뀜

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOrderGroup);
    }
}
