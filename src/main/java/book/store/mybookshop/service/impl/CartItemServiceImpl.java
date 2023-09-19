package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.cartitem.CartItemDto;
import book.store.mybookshop.dto.cartitem.ChangeQuantityRequestDto;
import book.store.mybookshop.exception.EntityNotFoundException;
import book.store.mybookshop.mapper.CartItemMapper;
import book.store.mybookshop.model.CartItem;
import book.store.mybookshop.repository.CartItemRepository;
import book.store.mybookshop.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository repository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemDto save(CartItem cartItem) {
        return cartItemMapper.toDto(repository.save(cartItem));
    }

    @Override
    public CartItem findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find item id: " + id)
        );
    }

    @Override
    public CartItemDto updateQuantity(Long id,
                                      ChangeQuantityRequestDto requestDto,
                                      CartItem cartItem) {
        cartItem.setQuantity(requestDto.getQuantity());
        repository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
