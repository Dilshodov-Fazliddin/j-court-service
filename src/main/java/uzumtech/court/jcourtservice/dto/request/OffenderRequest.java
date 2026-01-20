package uzumtech.court.jcourtservice.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OffenderRequest(

        @NotBlank(message = "Personal identification number is required")
        String personalIdentificationNumber,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Passport number is required")
        String passportNumber

) {
}
