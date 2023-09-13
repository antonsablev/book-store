package book.store.mybookshop.mapper;

import book.store.mybookshop.config.MapperConfig;
import book.store.mybookshop.dto.CreateUserRequestDto;
import book.store.mybookshop.dto.UserDto;
import book.store.mybookshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toModel(CreateUserRequestDto requestDto);

}
