package uzumtech.court.jcourtservice.service.abstraction;

import uzumtech.court.jcourtservice.entity.CourtDecision;

public interface WebhookService {
    void sendDecision(CourtDecision decision);
}
