package uzumtech.court.jcourtservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzumtech.court.jcourtservice.entity.WebhookSubscription;
import uzumtech.court.jcourtservice.exception.NotFoundException;
import uzumtech.court.jcourtservice.repository.WebhookSubscriptionRepository;
import uzumtech.court.jcourtservice.service.abstraction.WebhookService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final WebhookSubscriptionRepository repository;

    @Override
    public WebhookSubscription create(String url) {
        WebhookSubscription subscription = WebhookSubscription.builder()
                .url(url)
                .active(true)
                .build();

        return repository.save(subscription);
    }

    @Override
    public List<WebhookSubscription> getAll() {
        return repository.findAll();
    }

    @Override
    public WebhookSubscription activate(UUID id) {
        WebhookSubscription subscription = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Webhook not found"));

        subscription.setActive(true);
        return repository.save(subscription);
    }

    @Override
    public WebhookSubscription deactivate(UUID id) {
        WebhookSubscription subscription = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Webhook not found"));

        subscription.setActive(false);
        return repository.save(subscription);
    }
}
