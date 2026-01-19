package uzumtech.court.jcourtservice.service.abstraction;

import uzumtech.court.jcourtservice.entity.CourtDecision;
import uzumtech.court.jcourtservice.entity.WebhookSubscription;

import java.util.List;
import java.util.UUID;

public interface WebhookService {
    WebhookSubscription create(String url);

    List<WebhookSubscription> getAll();

    WebhookSubscription activate(UUID id);

    WebhookSubscription deactivate(UUID id);;
}
