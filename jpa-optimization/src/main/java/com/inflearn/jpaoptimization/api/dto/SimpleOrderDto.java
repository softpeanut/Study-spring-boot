package com.inflearn.jpaoptimization.api.dto;

import com.inflearn.jpaoptimization.domain.Address;
import com.inflearn.jpaoptimization.domain.order.Order;
import com.inflearn.jpaoptimization.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }

}
