package com.inflearn.jpaoptimization.api;

import com.inflearn.jpaoptimization.api.dto.SimpleOrderDto;
import com.inflearn.jpaoptimization.domain.order.Order;
import com.inflearn.jpaoptimization.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAll().stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
    }

}
