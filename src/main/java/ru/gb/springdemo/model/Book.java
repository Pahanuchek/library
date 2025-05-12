package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@Schema(name = "Книга")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "Идентификатор")
  private Long id;

  @Column(nullable = false)
  @Schema(name = "Название книги")
  private String name;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(name = "Выдачи книги")
  private List<Issue> issues = new ArrayList<>();

  public Book(String name) {
    this.name = name;
  }
}
