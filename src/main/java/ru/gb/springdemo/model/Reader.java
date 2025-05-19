package ru.gb.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Читатель")
@ToString(exclude = "issues")
@EqualsAndHashCode(exclude = "issues")
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
  @JsonIgnore
  private List<Issue> issues = new ArrayList<>();

  public Reader(String name) {
    this.name = name;
  }
}
