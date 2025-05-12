package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@Schema(name = "Выдача книги")
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "Идентификатор")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  @Schema(name = "Выданная книга")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reader_id", nullable = false)
  @Schema(name = "Читатель взявший книгу")
  private Reader reader;

  @Column(nullable = false)
  @Schema(name = "Дата выдача")
  private LocalDateTime issuedAt;

  @Column
  @Schema(name = "Дата возврата")
  private LocalDateTime returnedAt;

  public Issue(Book book, Reader reader) {
    this.book = book;
    this.reader = reader;
    this.issuedAt = LocalDateTime.now();
  }
}
