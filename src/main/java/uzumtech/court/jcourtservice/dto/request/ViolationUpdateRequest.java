package uzumtech.court.jcourtservice.dto.request;

import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;

import java.time.LocalDateTime;

public record ViolationUpdateRequest(
        String description,
        ViolationStatus violationStatus,
        LocalDateTime offenseTime
) {
}
