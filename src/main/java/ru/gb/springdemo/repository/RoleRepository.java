package ru.gb.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
