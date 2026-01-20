package uzumtech.court.jcourtservice.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uzumtech.court.jcourtservice.entity.*;
import uzumtech.court.jcourtservice.dto.request.*;
import uzumtech.court.jcourtservice.dto.response.*;

@Mapper(componentModel = "spring")
public interface OffenderMapper {
    @Mapping(target = "id", ignore = true)
    OffenderEntity toEntity(OffenderRequest request);
    OffenderEntity fromGcpToEntity(GcpResponse gcpResponse);
    OffenderResponse toResponse(OffenderEntity entity);
}
