package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reader_id", nullable = false)
  private Reader reader;

  @Column(nullable = false)
  private LocalDateTime issuedAt;

  @Column
  private LocalDateTime returnedAt;

  public Issue(Book book, Reader reader) {
    this.book = book;
    this.reader = reader;
    this.issuedAt = LocalDateTime.now();
  }
}
