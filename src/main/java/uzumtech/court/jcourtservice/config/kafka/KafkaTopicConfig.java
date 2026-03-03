package uzumtech.court.jcourtservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import uzumtech.court.jcourtservice.constant.KafkaConstant;

import static uzumtech.court.jcourtservice.constant.KafkaConstant.DECISION_CREATED;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic DecisionCreated() {
        return TopicBuilder.name(DECISION_CREATED).partitions(3).replicas(1).build();
    }
}
