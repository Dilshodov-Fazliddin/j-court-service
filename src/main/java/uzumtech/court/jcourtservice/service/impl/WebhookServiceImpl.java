package uzumtech.court.jcourtservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uzumtech.court.jcourtservice.entity.CourtDecision;
import uzumtech.court.jcourtservice.entity.WebhookSubscription;
import uzumtech.court.jcourtservice.repository.WebhookSubscriptionRepository;
import uzumtech.court.jcourtservice.service.abstraction.WebhookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final WebhookSubscriptionRepository webhookSubscriptionRepository;
    private final RestTemplate restTemplate;

    @Override
    public void sendDecision(CourtDecision decision) {
        List<WebhookSubscription> subscriptions =
                webhookSubscriptionRepository.findAllByActiveTrue();

        for (WebhookSubscription subscription : subscriptions) {
            send(subscription.getUrl(), decision);
        }
    }

    private void send(String url, CourtDecision decision) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CourtDecision> request =
                new HttpEntity<>(decision, headers);

        restTemplate.postForEntity(url, request, Void.class);
    }
}
