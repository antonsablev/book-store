package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.CategoryDto;
import book.store.mybookshop.dto.CreateCategoryRequestDto;
import book.store.mybookshop.exception.EntityNotFoundException;
import book.store.mybookshop.mapper.CategoryMapper;
import book.store.mybookshop.model.Category;
import book.store.mybookshop.repository.CategoryRepository;
import book.store.mybookshop.service.CategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(repository.save(category));
    }

    @Override
    public CategoryDto get(Long id) {
        return categoryMapper.toDto(repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category with id: " + id))
        );
    }

    @Override
    public List<CategoryDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CategoryDto updateById(Long id, CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        category.setId(id);
        repository.save(category);
        return categoryMapper.toDto(category);
    }

}
