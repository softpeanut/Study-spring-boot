package com.inflearn.jpaoptimization.api;

import com.inflearn.jpaoptimization.api.dto.OrderDto;
import com.inflearn.jpaoptimization.api.dto.OrderItemQueryDto;
import com.inflearn.jpaoptimization.api.dto.OrderQueryDto;
import com.inflearn.jpaoptimization.domain.order.Order;
import com.inflearn.jpaoptimization.repository.OrderQueryRepository;
import com.inflearn.jpaoptimization.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * V1. 엔티티 직접 노출
 * - 엔티티가 변하면 API 스펙이 변한다.
 * - 트랜잭션 안에서 지연 로딩 필요
 * - 양방향 연관관계 문제
 *
 * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용 X)
 * - 트랜잭션 안에서 지연 로딩 필요
 *
 * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용 O)
 * - 페이징 시에는 N 부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경 가능)
 *
 * V4. JPA에서 DTO로 바로 조회, 컬렉션 N 조회 (1 + N Query)
 * - 페이징 가능
 *
 * V5. JPA에서 DTO로 바로 조회, 컬렉션 1 조회 최적화 버전 (1 + 1 Query)
 * - 페이징 가능
 *
 * V6. JPA에서 DTO로 바로 조회, 플랫 데이터(1Query) (1 Query)
 * - 페이징 불가능
 */
@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    /**
     * V1. 엔티티 직접 노출
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll();
        for (Order order : all) { // Lazy 강제 초기화
            order.getMember().getName();
            order.getDelivery().getAddress();
            order.getOrderItems().stream()
                    .forEach(o -> o.getItem().getName());
        }
        return all;
    }

    /**
     * V2.엔티티를 조회해서 DTO로 변환 (fetch join 사용 X)
     * - 단점: 지연로딩으로 쿼리 N번 호출 (N+1문제 발생)
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll().stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
    }

    /**
     * V3.엔티티를 조회해서 DTO로 변환 (fetch join 사용 O)
     * - fetch join으로 쿼리 1번 호출
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderRepository.findAllWithItem().stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
    }

    /**
     * V3.1 엔티티를 조회해서 DTO로 변환 페이징 고려
     * - ToOne 관계만 우선 모두 페치 조인으로 최적화
     * - 컬렉션 관계는 hibernate.default_batch_fetch_size, @BatchSize로 최적화
     */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "100") int limit) {

        return orderRepository.findAllWithMemberDelivery(offset, limit).stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
    }

    /**
     * V4.DTO로 직접 조회
     * - ToOne 관계만 우선 조회
     * - ToMany 관계는 루프를 돌며 추가
     * - 단건 조회에서 많이 사용됨
     */
    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    /**
     * V5.컬렉션 조회 최적화
     * - ToOne 관계 한 번에 조회
     * - ToMany 관계 메모리에 미리 조회
     * - 데이터를 한꺼번에 처리할 때 많이 사용됨
     */
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    /**
     * V6.플랫 데이터 최적화
     * - 쿼리 1번
     * - 애플리케이션의 부담이 큼
     */
    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        return orderQueryRepository.findAllByDto_flat().stream()
                .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), toList())
                )).entrySet().stream()
                .map(e -> new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(), e.getValue()))
                .collect(toList());
    }

}
