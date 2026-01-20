package uzumtech.court.jcourtservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.court.jcourtservice.dto.request.ArticleRequest;
import uzumtech.court.jcourtservice.dto.response.ArticleResponse;
import uzumtech.court.jcourtservice.entity.ArticleEntity;

public interface ArticleService {
    ArticleResponse create(ArticleRequest articleRequest);
    void deleteArticleById(Long id);
    void updateById(Long id, ArticleRequest articleRequest);
    Page<ArticleResponse> getAll(Pageable pageable);
    ArticleResponse getArticleById(Long id);
}
