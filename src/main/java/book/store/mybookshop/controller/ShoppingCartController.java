package book.store.mybookshop.controller;

import book.store.mybookshop.dto.cartitem.CartItemDto;
import book.store.mybookshop.dto.cartitem.ChangeQuantityRequestDto;
import book.store.mybookshop.dto.cartitem.CreateCartItemRequestDto;
import book.store.mybookshop.dto.shoppingcart.ShoppingCartDto;
import book.store.mybookshop.service.CartItemService;
import book.store.mybookshop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Add products to cart",
        description = "Here you can find user cart, "
                + "add item to cart, edit quantity, and delete cart items")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @Operation(summary = "Get shopping cart by userID",
            description = "You can find shopping cart by UserID")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ShoppingCartDto getCart(Authentication authentication) {
        return shoppingCartService.findByUser(authentication);
    }

    @Operation(summary = "Add book to shopping Cart",
            description = "You can add book to shopping cart")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ShoppingCartDto addToCart(@RequestBody @Valid CreateCartItemRequestDto requestDto,
                                             Authentication authentication) {
        return shoppingCartService.addItemToCart(requestDto, authentication);
    }

    @Operation(summary = "Update book quantity",
            description = "Here you can update book quantity")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/cart-items/{cartItemId}")
    public CartItemDto updateQuantity(@PathVariable Long cartItemId,
                                      @RequestBody @Valid ChangeQuantityRequestDto requestDto,
                                      Authentication authentication) {
        return shoppingCartService.updateQuantity(cartItemId, requestDto, authentication);
    }

    @Operation(summary = "Delete book from shopping cart",
            description = "Here you can delete book from shopping cart")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/cart-items/{cartItemId}")
    public void deleteItem(@PathVariable Long cartItemId) {
        cartItemService.delete(cartItemId);
    }
}
