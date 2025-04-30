package ru.gb.springdemo.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.ReaderAddRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
@Slf4j
@AllArgsConstructor
public class ReaderController {
    private ReaderService readerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> addReader(ReaderAddRequest readerAddRequest) {
        log.info("Получен запрос на добавление читателя с именем: {}", readerAddRequest.getName());

        Reader reader = readerService.addReader(readerAddRequest);

        return ResponseEntity.ok(reader);
    }

    @GetMapping(path = "/{readerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> getReader(@PathVariable long readerId) {
        log.info("Получен запрос на получение читателя с id: {}", readerId);

        Reader reader = readerService.getReader(readerId);

        return ResponseEntity.ok(reader);
    }

    @DeleteMapping(path = "{readerId")
    public void deleteReader(@PathVariable long readerId) {
        log.info("Получен запрос на удаление читателя с id: {}", readerId);

        readerService.deleteReader(readerId);
    }

    @GetMapping(path = "/{readerId}/issue", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Issue>> getIssuesAtReader(@PathVariable long readerId) {
        log.info("Получен запрос на получение выдачей у читателя с id: {}", readerId);

        List<Issue> issues = readerService.getIssuesAtReader(readerId);

        return ResponseEntity.ok(issues);
    }

}
