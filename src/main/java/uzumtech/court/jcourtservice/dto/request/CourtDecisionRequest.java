package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record CourtDecisionRequest(

        @NotNull(message = "Violation id is required")
        Long violationId,

        @NotNull(message = "Decision type is required")
        DecisionType decisionType,

        @NotNull(message = "Fine amount is required")
        @Positive(message = "Fine amount must be greater than 0")
        Double fineAmount,

        @NotBlank(message = "Comment must not be blank")
        String comment
) {
}
