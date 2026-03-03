package uzumtech.court.jcourtservice.dto.event;

import lombok.Builder;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

@Builder
public record DecisionCreatedEvent(
        String  decisionNumber,
        Double fineAmount,
        String comment,
        DecisionType decisionType,
        String judge
) {
}
