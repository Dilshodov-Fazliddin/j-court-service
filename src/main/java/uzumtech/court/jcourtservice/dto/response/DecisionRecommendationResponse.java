package uzumtech.court.jcourtservice.dto.response;

import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record DecisionRecommendationResponse(
        DecisionType decisionType,
        String comment,
        String judgeName,
        Double fineAmount
) {
}
