package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.BookAddRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

@Slf4j
@RestController
@RequestMapping("/book")
@AllArgsConstructor
@Tag(name = "Books")
public class BookController {
    private BookService bookService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new book", description = "Добавляет новую книгу")
    public ResponseEntity<Book> addBook(@RequestBody BookAddRequest bookAddRequest) {
        log.info("Получен запрос на добавление книги с названием: {}", bookAddRequest.getName());

        Book book = bookService.addBook(bookAddRequest);

        return ResponseEntity.ok(book);
    }

    @GetMapping(path = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get book by id", description = "Получение книги по id")
    public ResponseEntity<Book> getBook(@PathVariable long bookId) {
        log.info("Получен запрос на получение книги с id: {}", bookId);

        Book book = bookService.getBook(bookId);

        return ResponseEntity.ok(book);
    }

    @DeleteMapping(path = "/{bookId}")
    @Operation(summary = "Delete book by id", description = "Удаление книги по id")
    public void deleteBook(@PathVariable long bookId) {
        log.info("Получен запрос на удаление книги с id: {}", bookId);

        bookService.deleteBook(bookId);
    }
}
