package ru.gb.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;

import java.util.List;

@Data
@AllArgsConstructor
public class ReaderBookInfo {
    private Reader reader;
    public List<Book> books;
}
