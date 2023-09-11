package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.CreateUserRequestDto;
import book.store.mybookshop.dto.UserDto;
import book.store.mybookshop.exception.EntityNotFoundException;
import book.store.mybookshop.exception.RegistrationException;
import book.store.mybookshop.mapper.UserMapper;
import book.store.mybookshop.model.Role;
import book.store.mybookshop.model.User;
import book.store.mybookshop.repository.RoleRepository;
import book.store.mybookshop.repository.UserRepository;
import book.store.mybookshop.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserDto save(CreateUserRequestDto userRequestDto) {
        if (userRepository.getByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Can't complete registration");
        }
        User user = userMapper.toModel(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRoles(Set.of(setRole(user.getEmail())));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    private Role setRole(String userEmail) {
        if (userEmail.startsWith("admin")) {
            return roleRepository.findByName(Role.RoleName.ADMIN)
                    .orElseThrow(() -> new EntityNotFoundException("Can't find Role"));
        } else {
            return roleRepository.findByName(Role.RoleName.USER)
                    .orElseThrow(() -> new EntityNotFoundException("Can't find Role"));
        }
    }
}
