package uzumtech.court.jcourtservice.mapper;

import org.mapstruct.*;
import uzumtech.court.jcourtservice.dto.request.ViolationRequest;
import uzumtech.court.jcourtservice.dto.request.ViolationUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ViolationResponse;
import uzumtech.court.jcourtservice.entity.ViolationEntity;

@Mapper(componentModel = "spring")
public interface ViolationMapper {

    @Mapping(target = "id", ignore = true)
    ViolationEntity toEntity(ViolationRequest request);

    ViolationResponse toResponse(ViolationEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateViolationFromDto(ViolationUpdateRequest dto, @MappingTarget ViolationEntity entity);
}
