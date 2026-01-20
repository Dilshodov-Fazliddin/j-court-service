package uzumtech.court.jcourtservice.dto.response;

import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record ArticleResponse(Long id,
                              String code,
                              Double fine,
                              DecisionType decisionType) {
}
