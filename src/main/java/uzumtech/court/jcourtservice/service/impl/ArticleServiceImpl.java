package uzumtech.court.jcourtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uzumtech.court.jcourtservice.dto.request.ArticleRequest;
import uzumtech.court.jcourtservice.dto.request.ArticleUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ArticleResponse;
import uzumtech.court.jcourtservice.entity.ArticleEntity;
import uzumtech.court.jcourtservice.exception.DataNotFoundException;
import uzumtech.court.jcourtservice.mapper.ArticleMapper;
import uzumtech.court.jcourtservice.repository.ArticleRepository;
import uzumtech.court.jcourtservice.service.ArticleService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    ArticleMapper articleMapper;
    ArticleRepository articleRepository;

    @Override
    public ArticleResponse create(ArticleRequest articleRequest) {
        var entity = articleMapper.toEntity(articleRequest);
        var save = articleRepository.save(entity);

        log.info("Article created {}", save);
        return articleMapper.toResponse(save);
    }

    @Override
    public void deleteArticleById(Long id) {
        var articleEntity = articleRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Article not found with id:" + id));

        log.info("Article deleted {}", articleEntity.getId());
        articleRepository.delete(articleEntity);
    }

    @Override
    public void updateById(Long id, ArticleUpdateRequest articleRequest) {
        var articleEntity = articleRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("The article with id:" + id + " not found"));

        articleMapper.updateArticleFromDto(articleRequest,articleEntity);
        log.info("Article updated with id={}", id);
    }

    @Override
    public Page<ArticleResponse> getAll(Pageable pageable) {
        Page<ArticleEntity> articleEntities = articleRepository.findAll(pageable);
        return articleEntities.map(articleMapper::toResponse);
    }

    @Override
    public ArticleResponse getArticleById(Long id) {
        var articleEntity = articleRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Article not found with id:" + id));
        return articleMapper.toResponse(articleEntity);
    }
}
