package book.store.mybookshop.service;

import book.store.mybookshop.dto.orderitem.OrderItemDto;
import book.store.mybookshop.model.Order;
import book.store.mybookshop.model.OrderItem;
import java.util.List;

public interface OrderItemService {
    OrderItemDto save(OrderItem orderItem);

    List<OrderItemDto> getAllByOrder(Order order);

    void delete(Long id);
}
