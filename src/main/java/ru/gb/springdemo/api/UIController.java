package ru.gb.springdemo.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springdemo.dto.ReaderBookInfo;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui")
@AllArgsConstructor
public class UIController {

    private BookService bookService;
    private ReaderService readerService;
    private IssuerService issuerService;



    @GetMapping("/books")
    public String books(Model model) {
        log.info("Получен запрос на получение всех книг");
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books", books);

        return "books";
    }

    @GetMapping("/readers")
    public String readers(Model model) {
        log.info("Получен запрос на получение всех читателей");
        List<Reader> readers = readerService.getAllReaders();

        model.addAttribute("readers", readers);

        return "reader";
    }

    @GetMapping("/issues")
    public String issuer(Model model) {
        log.info("Получен запрос на получение таблицы активности читателей");
        List<Issue> issues = issuerService.getAllIssue();

        model.addAttribute("issues", issues);

        return "issues";
    }

    @GetMapping("/reader/{id}")
    public String readerWithBooks(Model model, @PathVariable long id) {
        log.info("Получен запрос на получение читателей с книгами");
        ReaderBookInfo readerBookInfo = readerService.getReaderWithBooks(id);

        model.addAttribute("readerBooks", readerBookInfo);

        return "reader_book";
    }
}
