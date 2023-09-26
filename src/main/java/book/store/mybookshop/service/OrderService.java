package book.store.mybookshop.service;

import book.store.mybookshop.dto.order.OrderAddressRequestDto;
import book.store.mybookshop.dto.order.OrderDto;
import book.store.mybookshop.dto.orderitem.OrderItemDto;
import book.store.mybookshop.model.Order;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface OrderService {
    OrderDto save(Authentication authentication,
                  OrderAddressRequestDto requestDto);

    List<OrderDto> getAllByUserId(Authentication authentication,
                          Pageable pageable);

    List<OrderItemDto> getOrderItems(Long orderId);

    OrderItemDto getOrderItem(Long orderId, Long itemId);

    OrderDto updateStatus(Long id, Order.Status status);
}
