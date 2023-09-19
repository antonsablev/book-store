package book.store.mybookshop.mapper;

import book.store.mybookshop.config.MapperConfig;
import book.store.mybookshop.dto.book.BookDto;
import book.store.mybookshop.dto.book.BookDtoWithoutCategoryIds;
import book.store.mybookshop.dto.book.CreateBookRequestDto;
import book.store.mybookshop.model.Book;
import book.store.mybookshop.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toModel(CreateBookRequestDto createBookRequestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategoryIds(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoryIds(categoryIds);
    }
}
