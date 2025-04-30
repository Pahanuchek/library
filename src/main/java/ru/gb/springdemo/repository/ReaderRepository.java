package ru.gb.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
}
