package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record CourtDecisionRequest(

        @NotNull(message = "Violation id is required")
        Long violationId,

        @NotBlank(message = "Comment must not be blank")
        String comment
) {
}
