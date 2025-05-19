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
import ru.gb.springdemo.model.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    private static final String BOOK_URI = "/book";

    @Autowired
    private TestRestTemplate restTemplate;

    private BookAddRequest bookAddRequest;

    @BeforeEach
    public void setUp() {
        bookAddRequest = new BookAddRequest();
        bookAddRequest.setName("Лев");
    }

    @Test
    public void addBookSuccess() {
        ResponseEntity<Book> response = restTemplate.exchange(BOOK_URI, HttpMethod.POST,
                new HttpEntity<>(bookAddRequest), Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

    @Test
    public void getBookSuccess() {
        ResponseEntity<Book> addBookResponse = restTemplate.exchange(BOOK_URI, HttpMethod.POST,
                new HttpEntity<>(bookAddRequest), Book.class);

        assertEquals(HttpStatus.OK, addBookResponse.getStatusCode());
        assertNotNull(addBookResponse.getBody());

        ResponseEntity<Book> getBookResponse = restTemplate.exchange(BOOK_URI + "/" + addBookResponse.getBody().getId(),
                HttpMethod.GET, new HttpEntity<>(null), Book.class);

        assertEquals(HttpStatus.OK, getBookResponse.getStatusCode());
        assertNotNull(getBookResponse.getBody());
        assertEquals(addBookResponse.getBody(), getBookResponse.getBody());
    }

    @Test
    public void deleteBookSuccess() {
        ResponseEntity<Book> addBookResponse = restTemplate.exchange(BOOK_URI, HttpMethod.POST,
                new HttpEntity<>(bookAddRequest), Book.class);

        assertEquals(HttpStatus.OK, addBookResponse.getStatusCode());
        assertNotNull(addBookResponse.getBody());

        ResponseEntity<Book> getBookResponse = restTemplate.exchange(BOOK_URI + "/" + addBookResponse.getBody().getId(),
                HttpMethod.GET, new HttpEntity<>(null), Book.class);

        assertEquals(HttpStatus.OK, getBookResponse.getStatusCode());
        assertNotNull(getBookResponse.getBody());

        ResponseEntity<Object> deleteBookResponse = restTemplate.exchange(BOOK_URI + "/" + addBookResponse.getBody().getId(),
                HttpMethod.DELETE, new HttpEntity<>(null), Object.class);

        assertEquals(HttpStatus.OK, deleteBookResponse.getStatusCode());
        assertNull(deleteBookResponse.getBody());
    }

}