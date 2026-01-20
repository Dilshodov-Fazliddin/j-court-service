package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

public record ArticleRequest(

        @NotBlank(message = "Code must not be blank")
        String code,

        @NotBlank(message = "Title must not be blank")
        String title,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotNull(message = "Fine is required")
        Double fine,

        @NotNull(message = "Decision type is required")
        DecisionType decisionType
) {
}
