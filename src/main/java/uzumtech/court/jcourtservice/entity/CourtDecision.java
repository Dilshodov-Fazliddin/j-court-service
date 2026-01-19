package uzumtech.court.jcourtservice.entity;

import jakarta.persistence.*;
import lombok.*;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

@Entity
@Table(name = "court_decision")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtDecision extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "violation_id")
    private Violation violation;

    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    @Column(nullable = false)
    private double fineAmount;

    @Column(nullable = false)
    private String comment;
}
