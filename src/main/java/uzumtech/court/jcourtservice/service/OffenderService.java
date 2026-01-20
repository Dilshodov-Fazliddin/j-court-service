package uzumtech.court.jcourtservice.service;

import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;

public interface OffenderService {
    OffenderResponse create(OffenderRequest offenderRequest);
}
