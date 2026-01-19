package uzumtech.court.jcourtservice.service.abstraction;

import uzumtech.court.jcourtservice.dto.request.OffenderDto;
import uzumtech.court.jcourtservice.entity.Offender;


public interface OffenderService {
    Offender createOffender(OffenderDto offenderDto);
}
