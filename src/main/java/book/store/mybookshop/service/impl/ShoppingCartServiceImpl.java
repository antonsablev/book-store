package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.cartitem.CartItemDto;
import book.store.mybookshop.dto.cartitem.ChangeQuantityRequestDto;
import book.store.mybookshop.dto.cartitem.CreateCartItemRequestDto;
import book.store.mybookshop.dto.shoppingcart.ShoppingCartDto;
import book.store.mybookshop.exception.EntityNotFoundException;
import book.store.mybookshop.exception.NotEnoughProductsException;
import book.store.mybookshop.mapper.CartItemMapper;
import book.store.mybookshop.mapper.ShoppingCartMapper;
import book.store.mybookshop.model.CartItem;
import book.store.mybookshop.model.ShoppingCart;
import book.store.mybookshop.model.User;
import book.store.mybookshop.repository.ShoppingCartRepository;
import book.store.mybookshop.service.BookService;
import book.store.mybookshop.service.CartItemService;
import book.store.mybookshop.service.ShoppingCartService;
import book.store.mybookshop.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository repository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final BookService bookService;

    @Override
    public ShoppingCartDto findByUser(Authentication authentication) {
        ShoppingCart shoppingCart = findCart(authentication);
        if (shoppingCart != null) {
            return shoppingCartMapper.toDto(shoppingCart);
        } else {
            return shoppingCartMapper.toDto(createNewShoppingCart(authentication.getName()));
        }
    }

    @Override
    public ShoppingCartDto addItemToCart(CreateCartItemRequestDto requestDto,
                                         Authentication authentication) {
        ShoppingCart shoppingCart = findCart(authentication);
        Long bookId = requestDto.getBookId();
        Integer quantity = requestDto.getQuantity();
        CartItem cartItem = findCartItem(shoppingCart,
                requestDto.getBookId()).get();

        isEnoughQuantity(quantity, bookId, cartItem);

        updateCartItem(shoppingCart, requestDto);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public CartItemDto updateQuantity(Long itemId,
                                      ChangeQuantityRequestDto requestDto,
                                      Authentication authentication) {
        Integer amount = requestDto.getQuantity();
        CartItem cartItem = cartItemService.findById(itemId);
        Long bookId = cartItem.getBook().getId();

        isEnoughQuantity(amount, bookId, cartItem);

        return cartItemService.updateQuantity(itemId, requestDto);
    }

    private void updateCartItem(ShoppingCart shoppingCart, CreateCartItemRequestDto requestDto) {
        Optional<CartItem> cartItem = findCartItem(shoppingCart, requestDto.getBookId());
        if (cartItem.isPresent()) {
            cartItem.get().setQuantity(cartItem.get().getQuantity() + requestDto.getQuantity());
            cartItemService.save(cartItem.get());
        } else {
            CartItem newItem = cartItemMapper.toModel(requestDto);
            newItem.setShoppingCart(shoppingCart);
            newItem.setBook(bookService.findById(requestDto.getBookId()));
            cartItemService.save(newItem);
        }
    }

    private ShoppingCart createNewShoppingCart(String userEmail) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.findByEmail(userEmail));
        return repository.save(shoppingCart);
    }

    private ShoppingCart findCart(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return repository.getByUserId(user.getId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart")
        );
    }

    private void isEnoughQuantity(Integer amount,
                                  Long bookId,
                                  CartItem cartItem) {
        if (!(bookService.get(bookId).getQuantity() > (amount + cartItem.getQuantity()))) {
            throw new NotEnoughProductsException(
                    "Not enough products available in stock for book: " + bookId);
        }
    }

    private Optional<CartItem> findCartItem(ShoppingCart shoppingCart,
                                            Long bookId) {
        return shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();
    }
}
