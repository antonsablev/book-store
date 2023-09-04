package book.store.mybookshop.service;

import book.store.mybookshop.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
