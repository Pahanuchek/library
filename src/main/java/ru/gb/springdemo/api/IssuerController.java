package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssuerController {

  @Autowired
  private IssuerService service;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Issue book for reader", description = "Выдача книги читателю")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }

  @GetMapping(path = "/{issueId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Get issue by id", description = "Получение информации о выдаче книги по id")
  public ResponseEntity<Issue> getIssue(@PathVariable long issueId) {
    log.info("Получен запрос получение выдачи с id:  {}", issueId);

    Issue issue = service.getIssue(issueId);

    return ResponseEntity.ok(issue);
  }

  @PutMapping(path = "/{issueId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Returned book", description = "Возврат книги")
  public ResponseEntity<LocalDateTime> returnedBook(long issueId) {
    log.info("Получен запрос возврат книги, id выдачи:  {}", issueId);

    LocalDateTime date = service.returnedBook(issueId);

    return ResponseEntity.ok(date);
  }

}
