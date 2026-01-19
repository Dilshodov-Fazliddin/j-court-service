package uzumtech.court.jcourtservice.service.abstraction;

import uzumtech.court.jcourtservice.dto.request.OffenderDto;
import uzumtech.court.jcourtservice.dto.request.ViolationDto;
import uzumtech.court.jcourtservice.entity.Violation;

import java.util.List;
import java.util.UUID;

public interface ViolationService {
    Violation registerViolation(ViolationDto violationDto);

    Violation violationSent(UUID violationId);

    List<Violation> getOffenderViolations(String passportNumber);
}
