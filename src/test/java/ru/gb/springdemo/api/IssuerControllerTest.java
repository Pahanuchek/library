package ru.gb.springdemo.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gb.springdemo.dto.BookAddRequest;
import ru.gb.springdemo.dto.IssueRequest;
import ru.gb.springdemo.dto.ReaderAddRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IssuerControllerTest {

    private static final String BOOK_URI = "/book";
    private static final String READER_URI = "/reader";
    private static final String ISSUE_URI = "/issue";

    @Autowired
    private TestRestTemplate restTemplate;

    private BookAddRequest bookAddRequest;
    private ReaderAddRequest readerAddRequest;

    @BeforeEach
    public void setUp() {
        bookAddRequest = new BookAddRequest();
        bookAddRequest.setName("Лев");
        readerAddRequest = new ReaderAddRequest();
        readerAddRequest.setName("Иванов");
    }

    @Test
    public void issueBookSuccess() {
        ResponseEntity<Book> bookResponse = restTemplate.exchange(BOOK_URI, HttpMethod.POST,
                new HttpEntity<>(bookAddRequest), Book.class);
        assertEquals(HttpStatus.OK, bookResponse.getStatusCode());

        ResponseEntity<Reader> readerResponse = restTemplate.exchange(READER_URI, HttpMethod.POST,
                new HttpEntity<>(readerAddRequest), Reader.class);
        assertEquals(HttpStatus.OK, readerResponse.getStatusCode());

        IssueRequest issueRequest = new IssueRequest();
        issueRequest.setBookId(bookResponse.getBody().getId());
        issueRequest.setReaderId(readerResponse.getBody().getId());

        ResponseEntity<Issue> issueResponse = restTemplate.exchange(ISSUE_URI, HttpMethod.POST,
                new HttpEntity<>(issueRequest), Issue.class);

        assertEquals(HttpStatus.OK, issueResponse.getStatusCode());
        assertNotNull(issueResponse.getBody());
        assertEquals(bookResponse.getBody(), issueResponse.getBody().getBook());
        assertEquals(readerResponse.getBody(), issueResponse.getBody().getReader());
    }

    @Test
    public void getIssueSuccess() {
        ResponseEntity<Book> bookResponse = restTemplate.exchange(BOOK_URI, HttpMethod.POST,
                new HttpEntity<>(bookAddRequest), Book.class);
        assertEquals(HttpStatus.OK, bookResponse.getStatusCode());

        ResponseEntity<Reader> readerResponse = restTemplate.exchange(READER_URI, HttpMethod.POST,
                new HttpEntity<>(readerAddRequest), Reader.class);
        assertEquals(HttpStatus.OK, readerResponse.getStatusCode());

        IssueRequest issueRequest = new IssueRequest();
        issueRequest.setBookId(bookResponse.getBody().getId());
        issueRequest.setReaderId(readerResponse.getBody().getId());

        ResponseEntity<Issue> issueResponse = restTemplate.exchange(ISSUE_URI, HttpMethod.POST,
                new HttpEntity<>(issueRequest), Issue.class);
        assertEquals(HttpStatus.OK, issueResponse.getStatusCode());
        assertNotNull(issueResponse.getBody());

        ResponseEntity<Issue> getIssueResponse = restTemplate.exchange(ISSUE_URI + "/" + issueResponse.getBody().getId(),
                HttpMethod.GET, new HttpEntity<>(null), Issue.class);
        assertEquals(HttpStatus.OK, getIssueResponse.getStatusCode());
        assertNotNull(getIssueResponse.getBody());
        assertEquals(issueResponse.getBody().getId(), getIssueResponse.getBody().getId());
    }

}