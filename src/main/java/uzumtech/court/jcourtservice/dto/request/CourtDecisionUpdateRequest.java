package uzumtech.court.jcourtservice.dto.request;

import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record CourtDecisionUpdateRequest(
        DecisionType decisionType,

        Double fineAmount,

        String comment
) {
}
