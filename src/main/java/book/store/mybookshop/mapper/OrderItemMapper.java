package book.store.mybookshop.mapper;

import book.store.mybookshop.config.MapperConfig;
import book.store.mybookshop.dto.orderitem.OrderItemDto;
import book.store.mybookshop.dto.orderitem.OrderItemResponseDto;
import book.store.mybookshop.model.CartItem;
import book.store.mybookshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "orderId", source = "order.id")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "book", source = "book")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "book.price")
    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItem(CartItem cartItem);

    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem);
}
