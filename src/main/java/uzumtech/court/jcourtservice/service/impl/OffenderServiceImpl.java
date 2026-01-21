package uzumtech.court.jcourtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.court.jcourtservice.adapter.GcpAdapter;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.request.OffenderUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;
import uzumtech.court.jcourtservice.entity.OffenderEntity;
import uzumtech.court.jcourtservice.exception.DataNotFoundException;
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

    @Override
    public void deleteOffenderById(Long id) {
        OffenderEntity offenderEntity = offenderRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Offender not found with id" + id));
        offenderRepository.delete(offenderEntity);
    }

    @Override
    public Page<OffenderResponse> getAllOffenders(Pageable pageable) {
       var offenderEntities = offenderRepository.findAll(pageable);
       return offenderEntities.map(offenderMapper::toResponse);
    }

    @Override
    public OffenderResponse getOffenderByPersonalIdentificationNumber(String personalNumber) {
        var offenderEntity = offenderRepository
                .getByPersonalIdentificationNumber(personalNumber)
                .orElseThrow(() -> new DataNotFoundException("Offender not found with pid:" + personalNumber));

        return offenderMapper.toResponse(offenderEntity);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Long id, OffenderUpdateRequest offenderRequest) {
        OffenderEntity offenderEntity = offenderRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Offender not found with id" + id));

        offenderMapper.updateOffenderFromDto(offenderRequest,offenderEntity);
    }

}
