package uzumtech.court.jcourtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uzumtech.court.jcourtservice.adapter.GcpAdapter;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;
import uzumtech.court.jcourtservice.entity.OffenderEntity;
import uzumtech.court.jcourtservice.mapper.OffenderMapper;
import uzumtech.court.jcourtservice.repository.OffenderRepository;
import uzumtech.court.jcourtservice.service.OffenderService;
;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OffenderServiceImpl implements OffenderService {

    OffenderRepository offenderRepository;
    GcpAdapter gcpAdapter;
    OffenderMapper offenderMapper;

    @Override
    public OffenderResponse create(OffenderRequest offenderRequest) {
        var user = gcpAdapter.getUser(offenderRequest.personalIdentificationNumber());
        var offenderEntity = offenderMapper.fromGcpToEntity(user);
        var save = offenderRepository.save(offenderEntity);
        return offenderMapper.toResponse(save);
    }

}
