package book.store.mybookshop.dto.order;

import book.store.mybookshop.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusRequestDto {
    @NotNull
    private Order.Status status;
}
