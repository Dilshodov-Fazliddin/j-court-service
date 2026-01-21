package uzumtech.court.jcourtservice.dto.request;


public record OffenderUpdateRequest(
        String name,
        String surname,
        String address,
        String citizenship,
        String personalIdentificationNumber,
        Integer age
) {
}

