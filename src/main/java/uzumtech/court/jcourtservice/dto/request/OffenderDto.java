package uzumtech.court.jcourtservice.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OffenderDto {
    private String fullName;
    private String passportNumber;
    private LocalDate dateOfBirth;

}
