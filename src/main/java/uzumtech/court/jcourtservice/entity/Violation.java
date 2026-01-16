package uzumtech.court.jcourtservice.entity;


import jakarta.persistence.*;
import lombok.*;
import uzumtech.court.jcourtservice.constant.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "violations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Violation extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "offender_id")
    private Offender offender;

    @ManyToOne
    @JoinColumn(name = "articcle_id")
    private Article article;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;
}
