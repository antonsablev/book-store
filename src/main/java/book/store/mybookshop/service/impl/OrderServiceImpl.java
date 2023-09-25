package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.order.OrderAddressRequestDto;
import book.store.mybookshop.dto.order.OrderDto;
import book.store.mybookshop.dto.orderitem.OrderItemDto;
import book.store.mybookshop.exception.EntityNotFoundException;
import book.store.mybookshop.mapper.OrderItemMapper;
import book.store.mybookshop.mapper.OrderMapper;
import book.store.mybookshop.model.CartItem;
import book.store.mybookshop.model.Order;
import book.store.mybookshop.model.OrderItem;
import book.store.mybookshop.model.ShoppingCart;
import book.store.mybookshop.model.User;
import book.store.mybookshop.repository.OrderRepository;
import book.store.mybookshop.service.OrderItemService;
import book.store.mybookshop.service.OrderService;
import book.store.mybookshop.service.ShoppingCartService;
import book.store.mybookshop.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;
    private final OrderItemService orderItemService;
    private final UserService userService;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderDto save(Authentication authentication, OrderAddressRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartService.findCartById(authentication);
        Order order = createOrder(authentication, requestDto, shoppingCart);
        shoppingCartService.deleteById(shoppingCart.getId());

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getAllByUserId(Authentication authentication,
                                         Pageable pageable) {
        User user = userService.findByEmail(authentication.getName());

        return orderRepository.findAllByUserId(user.getId()).stream()
                .map(orderMapper::toDto).toList();
    }

    @Override
    public List<OrderItemDto> getOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order by id: " + orderId));
        return orderItemService.getAllByOrder(order);
    }

    @Override
    public OrderItemDto getOrderItem(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order by id: " + orderId));
        OrderItem orderItem = order.getOrderItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "There is no items in this order with id: " + itemId));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderDto updateStatus(Long id, Order.Status status) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find order with id: " + id));
        order.setStatus(status);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private BigDecimal getTotalAmount(ShoppingCart shoppingCart) {
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        return cartItems.stream().map(cartItem -> {
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
            BigDecimal price = new BigDecimal(String.valueOf(cartItem.getBook().getPrice()));
            return quantity.multiply(price);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order createOrder(Authentication authentication,
                              OrderAddressRequestDto requestDto,
                              ShoppingCart shoppingCart) {
        String address = requestDto.getShippingAddress();
        User user = userService.findByEmail(authentication.getName());
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.NEW);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(address);
        order.setTotal(getTotalAmount(shoppingCart));
        orderRepository.save(order);
        Set<OrderItem> orderItems = shoppingCart.getCartItems().stream()
                .map(orderItemMapper::toOrderItem)
                .collect(Collectors.toSet());

        order.setOrderItems(orderItems);
        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
        });
        orderItems.forEach(orderItemService::save);
        return order;
    }
}
