package uzumtech.court.jcourtservice.mapper;

import org.mapstruct.Mapping;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionRequest;
import uzumtech.court.jcourtservice.dto.response.CourtDecisionResponse;
import uzumtech.court.jcourtservice.entity.CourtDecisionEntity;

public interface CourtDecisionMapper {

    @Mapping(target = "id", ignore = true)
    CourtDecisionEntity toEntity(CourtDecisionRequest request);


    CourtDecisionResponse toResponse(CourtDecisionEntity entity);
}
