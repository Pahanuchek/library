package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
@Schema(name = "Читатель")
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "Идентификатор")
  private Long id;

  @Column(nullable = false)
  @Schema(name = "Имя читателя")
  private String name;

  @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(name = "Выдачи книги")
  private List<Issue> issues = new ArrayList<>();

  public Reader(String name) {
    this.name = name;
  }
}
