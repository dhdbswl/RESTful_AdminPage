package com.fastcampus.java.repasitory;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.OrderDetail;
import com.fastcampus.java.model.enumclass.OrderDetailStatus;
import com.fastcampus.java.repository.OrderDetailRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends JavaApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus(OrderDetailStatus.ORDERING);
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        //orderDetail.setOrderGroupId(1L); // 어떠한 장바구니에 / Long -> OrderGroup
        //orderDetail.setItemId(1L); // 어떠한 상품
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }
}
