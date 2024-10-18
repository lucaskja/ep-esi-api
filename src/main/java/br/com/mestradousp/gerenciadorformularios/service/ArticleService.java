package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.model.Article;
import br.com.mestradousp.gerenciadorformularios.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article createArticle(PerformanceReportCreateDto dto) {
        return this.articleRepository.save(Article.builder()
                .approvedArticles(dto.approvedArticles())
                .writingArticles(dto.writingArticles())
                .reviewingArticles(dto.reviewingArticles())
                .build()
        );
    }

    public void updateArticle(Article article) {
        this.articleRepository.save(article);
    }
}
