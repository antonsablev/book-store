package book.store.mybookshop.controller;

import book.store.mybookshop.dto.order.OrderAddressRequestDto;
import book.store.mybookshop.dto.order.OrderDto;
import book.store.mybookshop.dto.order.OrderStatusRequestDto;
import book.store.mybookshop.dto.orderitem.OrderItemDto;
import book.store.mybookshop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@Tag(name = "Orders Management", description = "Here you can add, get, update order")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    @Operation(summary = "Create order with Address",
            description = "Add shippingAddress to place an Order")
    public OrderDto createOrder(@RequestBody OrderAddressRequestDto requestDto,
                                Authentication authentication) {
        return orderService.save(authentication, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{orderId}")
    @Operation(summary = "Update order Status",
            description = "Enumerated statuses")
    public OrderDto updateOrderStatus(@PathVariable Long orderId,
                                      @RequestBody @Valid OrderStatusRequestDto requestDto) {
        return orderService.updateStatus(orderId, requestDto.getStatus());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Find all orders by user",
            description = "Just all users orders")
    List<OrderDto> getOrders(Pageable pageable,
                             Authentication authentication) {
        return orderService.getAllByUserId(authentication, pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get just items from your order",
            description = "Find order items using orderId")
    List<OrderItemDto> getOrderItems(@PathVariable Long orderId) {
        return orderService.getOrderItems(orderId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get item from order",
            description = "Get item from order with orderId and itemId")
    OrderItemDto getOrderItem(@PathVariable Long orderId,
                              @PathVariable Long itemId) {
        return orderService.getOrderItem(orderId, itemId);
    }
}
