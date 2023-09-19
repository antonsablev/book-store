package book.store.mybookshop.service;

import book.store.mybookshop.dto.user.CreateUserRequestDto;
import book.store.mybookshop.dto.user.UserDto;
import book.store.mybookshop.model.User;

public interface UserService {
    UserDto saveUser(CreateUserRequestDto userDto);

    UserDto saveAdmin(CreateUserRequestDto userDto);

    User findByEmail(String email);

}
