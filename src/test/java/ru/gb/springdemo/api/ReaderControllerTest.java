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
import ru.gb.springdemo.dto.ReaderAddRequest;
import ru.gb.springdemo.model.Reader;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReaderControllerTest {
    private static final String READER_URI = "/reader";

    @Autowired
    private TestRestTemplate restTemplate;

    private ReaderAddRequest readerAddRequest;

    @BeforeEach
    public void setUp() {
        readerAddRequest = new ReaderAddRequest();
        readerAddRequest.setName("Иванов");
    }

    @Test
    public void addReaderSuccess() {
        ResponseEntity<Reader> response = restTemplate.exchange(READER_URI, HttpMethod.POST,
                new HttpEntity<>(readerAddRequest), Reader.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

    @Test
    public void getReaderSuccess() {
        ResponseEntity<Reader> addReaderResponse = restTemplate.exchange(READER_URI, HttpMethod.POST,
                new HttpEntity<>(readerAddRequest), Reader.class);

        assertEquals(HttpStatus.OK, addReaderResponse.getStatusCode());
        assertNotNull(addReaderResponse.getBody());

        ResponseEntity<Reader> getReaderResponse = restTemplate.exchange(READER_URI + "/" + addReaderResponse.getBody().getId(),
                HttpMethod.GET, new HttpEntity<>(readerAddRequest), Reader.class);

        assertEquals(HttpStatus.OK, getReaderResponse.getStatusCode());
        assertNotNull(getReaderResponse.getBody());
        assertEquals(addReaderResponse.getBody(), getReaderResponse.getBody());
    }

    @Test
    public void deleteReaderSuccess() {
        ResponseEntity<Reader> addReaderResponse = restTemplate.exchange(READER_URI, HttpMethod.POST,
                new HttpEntity<>(readerAddRequest), Reader.class);

        assertEquals(HttpStatus.OK, addReaderResponse.getStatusCode());
        assertNotNull(addReaderResponse.getBody());

        ResponseEntity<Reader> getReaderResponse = restTemplate.exchange(READER_URI + "/" + addReaderResponse.getBody().getId(),
                HttpMethod.GET, new HttpEntity<>(readerAddRequest), Reader.class);

        assertEquals(HttpStatus.OK, getReaderResponse.getStatusCode());
        assertNotNull(getReaderResponse.getBody());

        ResponseEntity<Object> deleteReaderResponse = restTemplate.exchange(READER_URI + "/" + addReaderResponse.getBody().getId(),
                HttpMethod.DELETE, new HttpEntity<>(null), Object.class);

        assertEquals(HttpStatus.OK, deleteReaderResponse.getStatusCode());
        assertNull(deleteReaderResponse.getBody());
    }

}