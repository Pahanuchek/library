package ru.gb.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByName(String name);
}
