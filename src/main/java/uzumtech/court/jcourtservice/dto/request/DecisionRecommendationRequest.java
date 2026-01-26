package uzumtech.court.jcourtservice.dto.request;

import lombok.Builder;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;
import uzumtech.court.jcourtservice.entity.ViolationEntity;

@Builder
public record DecisionRecommendationRequest(
        ViolationEntity violation
) {
}
