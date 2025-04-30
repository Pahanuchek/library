package ru.gb.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
