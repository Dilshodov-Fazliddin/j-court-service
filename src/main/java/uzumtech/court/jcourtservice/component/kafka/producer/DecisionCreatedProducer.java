package uzumtech.court.jcourtservice.component.kafka.producer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import uzumtech.court.jcourtservice.constant.KafkaConstant;
import uzumtech.court.jcourtservice.dto.event.DecisionCreatedEvent;

import static uzumtech.court.jcourtservice.constant.KafkaConstant.DECISION_CREATED;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class DecisionCreatedProducer {
    KafkaTemplate<String, Object> kafkaTemplate;

    public void publishForDecisionCreatedTopic(DecisionCreatedEvent event){
        kafkaTemplate.send(DECISION_CREATED,event);
        log.info("Event sent {}", event);
    }
}
