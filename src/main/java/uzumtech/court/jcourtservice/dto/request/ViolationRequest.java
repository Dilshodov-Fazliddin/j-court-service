package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;

import java.time.LocalDateTime;

@Builder
public record ViolationRequest(

        @NotNull(message = "Article id is required")
        Long articleId,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotBlank(message = "PID must not be blank")
        String personalIdentificationNumber,

        @NotNull(message = "offense time is required")
        LocalDateTime offenseTime



) {
}
