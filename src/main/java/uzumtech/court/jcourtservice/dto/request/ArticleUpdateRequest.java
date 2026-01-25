package uzumtech.court.jcourtservice.dto.request;

import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record ArticleUpdateRequest(
        String code,

        String title,

        String description,

        Double fine,

        DecisionType decisionType
) {
}
