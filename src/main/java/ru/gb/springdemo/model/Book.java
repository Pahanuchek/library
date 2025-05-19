package ru.gb.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Книга")
@ToString(exclude = "issues")
@EqualsAndHashCode(exclude = "issues")
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
  @JsonIgnore
  private List<Issue> issues = new ArrayList<>();

  public Book(String name) {
    this.name = name;
  }
}
