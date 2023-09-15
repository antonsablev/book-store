package book.store.mybookshop.mapper;

import book.store.mybookshop.config.MapperConfig;
import book.store.mybookshop.dto.CategoryDto;
import book.store.mybookshop.dto.CreateCategoryRequestDto;
import book.store.mybookshop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category toModel(CreateCategoryRequestDto createCategoryRequestDto);
}
