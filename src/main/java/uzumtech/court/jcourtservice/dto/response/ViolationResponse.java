package uzumtech.court.jcourtservice.dto.response;

import uzumtech.court.jcourtservice.constant.enums.Status;

public record ViolationResponse(

        Long id,
        String description,
        Status status,

        Long offenderId,
        Long articleId
) {
}
