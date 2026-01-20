package uzumtech.court.jcourtservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uzumtech.court.jcourtservice.dto.request.ViolationRequest;
import uzumtech.court.jcourtservice.dto.response.ViolationResponse;
import uzumtech.court.jcourtservice.entity.ViolationEntity;

@Mapper(componentModel = "spring")
public interface ViolationMapper {

    @Mapping(target = "id", ignore = true)
    ViolationEntity toEntity(ViolationRequest request);

    ViolationResponse toResponse(ViolationEntity entity);

}
