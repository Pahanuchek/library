package ru.gb.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.Issue;

public interface IssuerRepository extends CrudRepository<Issue, Long> {
}
