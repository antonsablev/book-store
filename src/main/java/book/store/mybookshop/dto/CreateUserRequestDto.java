package book.store.mybookshop.dto;

import book.store.mybookshop.lib.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords don't match")
public class CreateUserRequestDto {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 4, message = "Password must be at least 6 characters long")
    @NotBlank(message = "Password is required")
    private String password;

    @Size(min = 4, message = "Password must be at least 6 characters long")
    @NotBlank(message = "Password is required")
    private String repeatPassword;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Size(max = 255, message = "Shipping address must not exceed 255 characters")
    private String shippingAddress;
}
