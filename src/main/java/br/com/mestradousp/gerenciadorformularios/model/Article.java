package br.com.mestradousp.gerenciadorformularios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    private Integer writingArticles;

    @Min(0)
    private Integer reviewingArticles;

    @Min(0)
    private Integer approvedArticles;
}

