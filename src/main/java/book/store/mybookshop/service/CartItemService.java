package book.store.mybookshop.service;

import book.store.mybookshop.dto.cartitem.CartItemDto;
import book.store.mybookshop.dto.cartitem.ChangeQuantityRequestDto;
import book.store.mybookshop.model.CartItem;

public interface CartItemService {

    CartItemDto save(CartItem cartItem);

    void delete(Long id);

    CartItem findById(Long id);

    CartItemDto updateQuantity(Long id,
                               ChangeQuantityRequestDto requestDto,
                               CartItem cartItem);

}
