package uzumtech.court.jcourtservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.request.OffenderUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;

public interface OffenderService {
    OffenderResponse create(OffenderRequest offenderRequest);
    void deleteOffenderById(Long id);
    Page<OffenderResponse>getAllOffenders(Pageable pageable);
    OffenderResponse getOffenderByPersonalIdentificationNumber(String personalNumber);
    void update(Long id, OffenderUpdateRequest offenderRequest);

}
