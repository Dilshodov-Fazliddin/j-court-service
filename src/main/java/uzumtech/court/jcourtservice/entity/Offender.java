package uzumtech.court.jcourtservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "offenders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offender extends BaseEntity{

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String passwordNumber;

    @Column(nullable = false)
    private LocalDate birthDate;


}
