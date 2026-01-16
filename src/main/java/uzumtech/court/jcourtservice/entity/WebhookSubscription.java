package uzumtech.court.jcourtservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "webhook_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebhookSubscription extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private boolean active;

}
