package ss7.bt.service;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T,E> {
    List<T> findAll();
    Optional<T> findById(E e);
    T save (T t);
    void deleteById(E e);
}
