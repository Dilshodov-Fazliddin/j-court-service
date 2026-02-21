package uzumtech.court.jcourtservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.request.OffenderUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;
import uzumtech.court.jcourtservice.entity.OffenderEntity;

public interface OffenderService {
    OffenderEntity create(OffenderRequest offenderRequest);
    Page<OffenderResponse>getAllOffenders(Pageable pageable);
    OffenderResponse getOffenderByPersonalIdentificationNumber(String personalNumber);
    void update(Long id, OffenderUpdateRequest offenderRequest);

}
