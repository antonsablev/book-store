package book.store.mybookshop.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeBookQuantityRequest {
    @NotNull
    private Integer quantity;
}
