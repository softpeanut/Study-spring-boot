package com.inflearn.jpaoptimization.api.dto;

import com.inflearn.jpaoptimization.domain.delivery.Address;
import com.inflearn.jpaoptimization.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderFlatDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private Address address;
    private OrderStatus orderStatus;

    private String itemName;
    private int orderPrice;
    private int count;

}
