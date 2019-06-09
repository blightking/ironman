package guru.springframework.ironman.service.asbt;

import java.util.List;

public interface BaseGenericService<T> {
    T getDataById(Long id);

    T save(T data);

    List<T> findAll();

    void deleteById(Long id);

    T update(T data);
}
