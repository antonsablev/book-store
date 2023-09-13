package book.store.mybookshop.controller;

import book.store.mybookshop.dto.CreateUserRequestDto;
import book.store.mybookshop.dto.UserDto;
import book.store.mybookshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto registerAdmin(@RequestBody @Valid CreateUserRequestDto userDto) {
        return userService.saveAdmin(userDto);
    }
}
