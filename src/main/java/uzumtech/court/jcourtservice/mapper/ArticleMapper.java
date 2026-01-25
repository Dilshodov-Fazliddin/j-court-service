package uzumtech.court.jcourtservice.mapper;

import org.mapstruct.*;
import uzumtech.court.jcourtservice.dto.request.ArticleRequest;
import uzumtech.court.jcourtservice.dto.request.ArticleUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ArticleResponse;
import uzumtech.court.jcourtservice.entity.ArticleEntity;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    ArticleEntity toEntity(ArticleRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateArticleFromDto(ArticleUpdateRequest dto, @MappingTarget ArticleEntity entity);

    ArticleResponse toResponse(ArticleEntity entity);
}
