package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record OffenderRequest(

        @NotBlank(message = "Personal identification number is required")
        String personalIdentificationNumber,

        @NotNull(message = "Passport number is required")
        Long articleId
) {
}
