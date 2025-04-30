package ru.gb.springdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.dto.ReaderAddRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ReaderService {
    private ReaderRepository readerRepository;

    public Reader addReader(ReaderAddRequest readerAddRequest) {
        if (readerAddRequest == null) {
            throw new RuntimeException("Запрос на добавление читателя не может быть пустым");
        }

        return readerRepository.save(new Reader(readerAddRequest.getName()));
    }

    public Reader getReader(long readerId) {
        return readerRepository.findById(readerId)
                .orElseThrow(() -> new NoSuchElementException("Читатель с данным идентификатором отсутствует"));
    }

    public void deleteReader(long readerId) {
        readerRepository.deleteById(readerId);
    }

    public List<Issue> getIssuesAtReader(long readerId) {
        Reader reader = getReader(readerId);

        return reader.getIssues();
    }
}
