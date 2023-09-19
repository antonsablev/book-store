package book.store.mybookshop.controller;

import book.store.mybookshop.dto.book.BookDtoWithoutCategoryIds;
import book.store.mybookshop.dto.category.CategoryDto;
import book.store.mybookshop.dto.category.CreateCategoryRequestDto;
import book.store.mybookshop.service.BookService;
import book.store.mybookshop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categories Management", description = "Here you can add, delete, update categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get All categories",
            description = "Here you can find list with all categories")
    @GetMapping
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get category by Id", description = "You can find category by Id")
    @GetMapping("/{id}")
    public CategoryDto getById(@Parameter(description = "The category id") @PathVariable Long id) {
        return categoryService.get(id);
    }

    @Operation(summary = "Create category", description = "Create category with required fields")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CategoryDto create(@RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @Operation(summary = "Update Category", description = "Update category by id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@Parameter(description = "The category id") @PathVariable Long id,
                              @RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.updateById(id, requestDto);
    }

    @Operation(summary = "Delete category", description = "Delete category by id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "The category id") @PathVariable Long id) {
        categoryService.delete(id);
    }

    @Operation(summary = "Get book by CategoryId",
            description = "Here you can find list with all categories")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.getByCategory(id);
    }
}
