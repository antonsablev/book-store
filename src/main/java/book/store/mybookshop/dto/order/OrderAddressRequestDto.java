package book.store.mybookshop.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderAddressRequestDto {
    @NotNull
    private String shippingAddress;
}
