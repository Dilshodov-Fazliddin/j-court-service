package uzumtech.court.jcourtservice.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offender_id")
    private Offender offender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articcle_id")
    private Article article;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;
}
