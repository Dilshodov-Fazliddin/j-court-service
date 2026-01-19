package uzumtech.court.jcourtservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViolationDto {
    private String offenderPassportNumber;
    private String articleCode;
}
