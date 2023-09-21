package book.store.mybookshop.dto.cartitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeQuantityRequestDto {
    @NotNull
    @Min(0)
    private Integer quantity;
}
