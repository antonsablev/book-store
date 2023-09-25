package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.orderitem.OrderItemDto;
import book.store.mybookshop.mapper.OrderItemMapper;
import book.store.mybookshop.model.Order;
import book.store.mybookshop.model.OrderItem;
import book.store.mybookshop.repository.OrderItemRepository;
import book.store.mybookshop.service.OrderItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDto save(OrderItem orderItem) {
        return orderItemMapper.toDto(orderItemRepository.save(orderItem));
    }

    @Override
    public List<OrderItemDto> getAllByOrder(Order order) {
        return order.getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
