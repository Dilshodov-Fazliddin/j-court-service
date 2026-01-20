package uzumtech.court.jcourtservice.dto.response;

public record GcpResponse(
        String name,
        String surname,
        String address,
        Integer age,
        String citizenship,
        String personalIdentificationNumber,
        String passportNumber
) {
}
