package book.store.mybookshop.controller;

import book.store.mybookshop.dto.user.CreateUserRequestDto;
import book.store.mybookshop.dto.user.UserDto;
import book.store.mybookshop.dto.user.UserLoginRequestDto;
import book.store.mybookshop.dto.user.UserLoginResponseDto;
import book.store.mybookshop.security.AuthenticationService;
import book.store.mybookshop.service.UserService;
import jakarta.validation.Valid;
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
    public UserDto register(@RequestBody @Valid CreateUserRequestDto userDto) {
        return userService.saveUser(userDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
