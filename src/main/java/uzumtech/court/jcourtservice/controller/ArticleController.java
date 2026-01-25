package uzumtech.court.jcourtservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.dto.request.ArticleRequest;
import uzumtech.court.jcourtservice.dto.request.ArticleUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ArticleResponse;
import uzumtech.court.jcourtservice.service.ArticleService;

@RestController
@RequestMapping("/api/court/articles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ArticleController {

    ArticleService articleService;

    @PostMapping
    public ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest) {
        return articleService.create(articleRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
    }

    @PutMapping("/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequest articleRequest) {
        articleService.updateById(id, articleRequest);
    }

    @GetMapping
    public Page<ArticleResponse> getAllArticles(Pageable pageable) {
        return articleService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }
}
