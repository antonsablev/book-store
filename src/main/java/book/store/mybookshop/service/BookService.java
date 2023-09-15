package book.store.mybookshop.service;

import book.store.mybookshop.dto.BookDto;
import book.store.mybookshop.dto.BookDtoWithoutCategoryIds;
import book.store.mybookshop.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    BookDto get(Long id);

    List<BookDto> findAll(Pageable pageable);

    void delete(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> findByTitle(String bookTitle);

    List<BookDtoWithoutCategoryIds> getByCategory(Long id);

    void addCategoryToId(Long bookId, Long categoryId);
}
