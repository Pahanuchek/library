package ru.gb.springdemo.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;

@Configuration
@AllArgsConstructor
public class ConfigItems {

    private BookRepository bookRepository;
    private ReaderRepository readerRepository;
    private IssuerRepository issuerRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;


    @PostConstruct
    public void init() {
        Book book1 = new Book("Война и мир");
        Book book2 = new Book("На западном фронте без перемен");
        Book book3 = new Book("Три товарища");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        Reader reader1 = new Reader("Андрей");
        Reader reader2 = new Reader("Григорий");
        Reader reader3 = new Reader("Владимир");

        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        issuerRepository.save(new Issue(book1, reader1));
        issuerRepository.save(new Issue(book2, reader1));
        issuerRepository.save(new Issue(book3, reader2));

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");

        User user = new User("user", "user");
        user.addRole(userRole);
        User admin = new User("admin", "admin");
        admin.addRole(adminRole);

        userRepository.save(user);
        userRepository.save(admin);

    }
}
