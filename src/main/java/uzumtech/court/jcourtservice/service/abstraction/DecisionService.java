package uzumtech.court.jcourtservice.service.abstraction;

import uzumtech.court.jcourtservice.entity.CourtDecision;

import java.util.UUID;


public interface DecisionService {
    CourtDecision makeDecision(UUID violationId);
}
