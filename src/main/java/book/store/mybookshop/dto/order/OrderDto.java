package book.store.mybookshop.dto.order;

import book.store.mybookshop.dto.orderitem.OrderItemResponseDto;
import book.store.mybookshop.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Order.Status status;
    private BigDecimal total;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private Set<OrderItemResponseDto> orderItems;
}
