package uzumtech.court.jcourtservice.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzumtech.court.jcourtservice.entity.WebhookSubscription;

import java.util.UUID;

@Repository
public interface WebhookSubscriptionRepository extends JpaRepository<WebhookSubscription, UUID> {
}
