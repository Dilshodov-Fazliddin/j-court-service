package uzumtech.court.jcourtservice.dto.response;

import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;

public record ViolationResponse(

        Long id,
        String description,
        ViolationStatus status
) {
}
