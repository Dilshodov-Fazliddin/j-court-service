package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;

import java.time.LocalDateTime;

public record ViolationRequest(

        @NotNull(message = "Offender id is required")
        Long offenderId,

        @NotNull(message = "Article id is required")
        Long articleId,

        @NotBlank(message = "Description must not be blank")
        String description,

        ViolationStatus violationStatus,

        @NotNull(message = "offense time is required")
        LocalDateTime offenseTime

) {
}
