package book.store.mybookshop.service;

import book.store.mybookshop.dto.cartitem.CartItemDto;
import book.store.mybookshop.dto.cartitem.ChangeQuantityRequestDto;
import book.store.mybookshop.dto.cartitem.CreateCartItemRequestDto;
import book.store.mybookshop.dto.shoppingcart.ShoppingCartDto;
import book.store.mybookshop.model.ShoppingCart;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {

    ShoppingCartDto findByUser(Authentication authentication);

    ShoppingCartDto addItemToCart(CreateCartItemRequestDto requestDto,
                                  Authentication authentication);

    CartItemDto updateQuantity(Long itemId,
                               ChangeQuantityRequestDto requestDto,
                               Authentication authentication);

    ShoppingCart findCartById(Authentication authentication);

    void deleteById(Long id);
}
