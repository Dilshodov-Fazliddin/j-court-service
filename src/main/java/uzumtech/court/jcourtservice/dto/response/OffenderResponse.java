package uzumtech.court.jcourtservice.dto.response;

import java.time.LocalDate;

public record OffenderResponse(
        Long id,
        String name,
        String surname,
        Integer age,
        String citizenship
) {
}
