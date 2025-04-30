package ru.gb.springdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.dto.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssuerRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class IssuerService {

  private BookRepository bookRepository;
  private ReaderRepository readerRepository;
  private IssuerRepository issuerRepository;

  @Value("${application.issue.max-allowed-books:1}")
  private int maxAllowedBooks;

  public Issue issue(IssueRequest request) {

    Book book = bookRepository.findById(request.getBookId())
            .orElseThrow(() -> new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\""));

    Reader reader = readerRepository.findById(request.getReaderId())
            .orElseThrow(() -> new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\""));

    int countBook = (int) reader.getIssues().stream().filter(b -> b.getReturnedAt() != null).count();
    if (countBook >= maxAllowedBooks) {
      throw  new RuntimeException("У данного читателя превышен лимит книг на руках");
    }

    Issue issue = new Issue(book, reader);

    return issuerRepository.save(issue);
  }

  public Issue getIssue(long issueId) {
    return issuerRepository.findById(issueId)
            .orElseThrow(() -> new NoSuchElementException("Выдача с данным идентификатором отсутствует"));
  }

  public LocalDateTime returnedBook(long issueId) {
    Issue issue = getIssue(issueId);
    issue.setReturnedAt(LocalDateTime.now());
    issue = issuerRepository.save(issue);

    return issue.getReturnedAt();
  }

}
