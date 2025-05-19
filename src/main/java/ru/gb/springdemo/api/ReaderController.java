package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Reader")
public class ReaderController {
    private ReaderService readerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new reader", description = "Добавление нового читателя")
    public ResponseEntity<Reader> addReader(@RequestBody ReaderAddRequest readerAddRequest) {
        log.info("Получен запрос на добавление читателя с именем: {}", readerAddRequest.getName());

        Reader reader = readerService.addReader(readerAddRequest);

        return ResponseEntity.ok(reader);
    }

    @GetMapping(path = "/{readerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get reader by id", description = "Получение читателя по id")
    public ResponseEntity<Reader> getReader(@PathVariable long readerId) {
        log.info("Получен запрос на получение читателя с id: {}", readerId);

        Reader reader = readerService.getReader(readerId);

        return ResponseEntity.ok(reader);
    }

    @DeleteMapping(path = "{readerId}")
    @Operation(summary = "Delete reader by id", description = "Удаление читателя по id")
    public void deleteReader(@PathVariable long readerId) {
        log.info("Получен запрос на удаление читателя с id: {}", readerId);

        readerService.deleteReader(readerId);
    }

    @GetMapping(path = "/{readerId}/issue", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get issues by reader", description = "Получение списка выдачи книг читателю")
    public ResponseEntity<List<Issue>> getIssuesAtReader(@PathVariable long readerId) {
        log.info("Получен запрос на получение выдачей у читателя с id: {}", readerId);

        List<Issue> issues = readerService.getIssuesAtReader(readerId);

        return ResponseEntity.ok(issues);
    }

}
