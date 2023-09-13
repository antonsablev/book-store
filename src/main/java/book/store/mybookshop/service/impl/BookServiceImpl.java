package book.store.mybookshop.service.impl;

import book.store.mybookshop.dto.BookDto;
import book.store.mybookshop.dto.CreateBookRequestDto;
import book.store.mybookshop.exception.EntityNotFoundException;
import book.store.mybookshop.mapper.BookMapper;
import book.store.mybookshop.model.Book;
import book.store.mybookshop.repository.BookRepository;
import book.store.mybookshop.service.BookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto get(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book with id: " + id)));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book updatedBook = bookMapper.toModel(requestDto);
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public List<BookDto> findByTitle(String bookTitle) {
        return bookRepository.findAllByTitleContainingIgnoreCase(bookTitle).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
