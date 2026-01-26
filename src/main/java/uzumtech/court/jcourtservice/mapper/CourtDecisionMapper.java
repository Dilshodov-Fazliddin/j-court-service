package uzumtech.court.jcourtservice.mapper;

import org.mapstruct.*;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionRequest;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionUpdateRequest;
import uzumtech.court.jcourtservice.dto.request.ViolationUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.CourtDecisionResponse;
import uzumtech.court.jcourtservice.entity.CourtDecisionEntity;
import uzumtech.court.jcourtservice.entity.ViolationEntity;

@Mapper(componentModel = "spring")
public interface CourtDecisionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "decisionStatus", constant = "SUBMITTED")
    CourtDecisionEntity toEntity(CourtDecisionRequest request);


    CourtDecisionResponse toResponse(CourtDecisionEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCourtDecisionFromDto(CourtDecisionUpdateRequest dto, @MappingTarget CourtDecisionEntity entity);
}
