package book.store.mybookshop.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AbstractService<R, C> {
    R save(C c);

    R get(Long id);

    List<R> findAll(Pageable pageable);

    void delete(Long id);

    R updateById(Long id, C c);
}
