package book.store.mybookshop.controller;

import book.store.mybookshop.dto.CreateUserRequestDto;
import book.store.mybookshop.dto.UserDto;
import book.store.mybookshop.dto.UserLoginRequestDto;
import book.store.mybookshop.dto.UserLoginResponseDto;
import book.store.mybookshop.security.AuthenticationService;
import book.store.mybookshop.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserDto login(@RequestBody CreateUserRequestDto userDto) {
        return userService.save(userDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
