package com.inflearn.jpaoptimization.api.dto;

import com.inflearn.jpaoptimization.domain.Address;
import com.inflearn.jpaoptimization.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

}
