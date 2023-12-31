package book.store.mybookshop.controller;

import book.store.mybookshop.dto.book.BookDto;
import book.store.mybookshop.dto.book.ChangeBookQuantityRequest;
import book.store.mybookshop.dto.book.CreateBookRequestDto;
import book.store.mybookshop.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoint for book management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Get all books",
            description = "You can get all books with sorting and pagination.")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get book by id", description = "You can get one book by it's id.")
    public BookDto getById(@Parameter(description = "The book id") @PathVariable Long id) {
        return bookService.get(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create book", description = "You can create new book")
    public BookDto create(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-category-to-book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add category to book", description = "You can add category to book")
    public void addCategoryToBook(@PathVariable Long bookId, @RequestParam Long categoryId) {
        bookService.addCategoryToId(bookId, categoryId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update book", description = "You can update book information by Id")
    public BookDto update(@Parameter(description = "The book Id") @PathVariable Long id,
                          @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.updateById(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-quantity/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update book quantity", description = "You can update book quantity by Id")
    public BookDto updateQuantity(@Parameter(description = "The book Id") @PathVariable Long id,
                                  @RequestBody @Valid ChangeBookQuantityRequest requestDto) {
        return bookService.updateQuantity(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Book", description = "Soft delete book by id")
    public void delete(@Parameter(description = "The book Id") @PathVariable Long id) {
        bookService.delete(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/search")
    @Operation(summary = "Get book by Name", description = "You can find needed book by it's Title")
    public List<BookDto> search(@Parameter(description = "The book Title")
                                @RequestParam String bookTitle) {
        return bookService.findByTitle(bookTitle);
    }
}
