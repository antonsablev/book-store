package book.store.mybookshop.service;

import book.store.mybookshop.dto.CategoryDto;
import book.store.mybookshop.dto.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    CategoryDto get(Long id);

    List<CategoryDto> getAll(Pageable pageable);

    void delete(Long id);

    CategoryDto updateById(Long id, CreateCategoryRequestDto requestDto);

}
