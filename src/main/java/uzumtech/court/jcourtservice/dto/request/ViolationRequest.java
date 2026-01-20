package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ViolationRequest(

        @NotNull(message = "Offender id is required")
        Long offenderId,

        @NotNull(message = "Article id is required")
        Long articleId,

        @NotBlank(message = "Description must not be blank")
        String description
) {
}
