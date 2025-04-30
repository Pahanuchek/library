package ru.gb.springdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.dto.BookAddRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Book addBook(BookAddRequest bookAddRequest) {
        if (bookAddRequest == null) {
            throw new RuntimeException("Книга не может быть пустой");
        }

        return bookRepository.save(new Book(bookAddRequest.getName()));
    }

    public Book getBook(long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Не найдена книга с идентификатором: " + bookId));

    }

    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
