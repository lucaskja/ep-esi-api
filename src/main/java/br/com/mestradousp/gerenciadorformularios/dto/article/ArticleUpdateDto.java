package br.com.mestradousp.gerenciadorformularios.dto.article;

import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record ArticleUpdateDto(
        @Min(0)
        Integer writingArticles,

        @Min(0)
        Integer reviewingArticles,

        @Min(0)
        Integer approvedArticles
) {
}
