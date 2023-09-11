package book.store.mybookshop.service;

import book.store.mybookshop.dto.CreateUserRequestDto;
import book.store.mybookshop.dto.UserDto;

public interface UserService {
    UserDto save(CreateUserRequestDto userDto);
}
