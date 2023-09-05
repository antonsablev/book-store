package book.store.mybookshop.service;

import book.store.mybookshop.dto.BookDto;
import book.store.mybookshop.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    BookDto get(Long id);

    List<BookDto> findAll();

    void delete(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> findByTitle(String bookTitle);

}
