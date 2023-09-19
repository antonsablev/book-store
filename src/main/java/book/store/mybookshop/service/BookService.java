package book.store.mybookshop.service;

import book.store.mybookshop.dto.book.BookDto;
import book.store.mybookshop.dto.book.BookDtoWithoutCategoryIds;
import book.store.mybookshop.dto.book.ChangeBookQuantityRequest;
import book.store.mybookshop.dto.book.CreateBookRequestDto;
import book.store.mybookshop.model.Book;
import java.util.List;

public interface BookService extends AbstractService<BookDto, CreateBookRequestDto> {

    List<BookDto> findByTitle(String bookTitle);

    List<BookDtoWithoutCategoryIds> getByCategory(Long id);

    void addCategoryToId(Long bookId, Long categoryId);

    Book findById(Long id);

    BookDto updateQuantity(Long id, ChangeBookQuantityRequest requestDto);
}
