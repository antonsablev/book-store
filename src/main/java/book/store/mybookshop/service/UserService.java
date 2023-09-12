package book.store.mybookshop.service;

import book.store.mybookshop.dto.CreateUserRequestDto;
import book.store.mybookshop.dto.UserDto;

public interface UserService {
    UserDto saveUser(CreateUserRequestDto userDto);

    UserDto saveAdmin(CreateUserRequestDto userDto);
}
