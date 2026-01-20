package uzumtech.court.jcourtservice.dto.response;

import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record CourtDecisionResponse(

        Long id,
        DecisionType decisionType,
        Double fineAmount,
        String comment,
        Long violationId
) {
}
