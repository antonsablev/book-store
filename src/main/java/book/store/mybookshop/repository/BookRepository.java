package book.store.mybookshop.repository;

import book.store.mybookshop.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id=:categoryId")
    List<Book> findBookByCategory(@Param("categoryId") Long categoryId);
}
