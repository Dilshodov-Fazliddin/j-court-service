package uzumtech.court.jcourtservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.court.jcourtservice.constant.enums.DecisionStatus;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourtDecisionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String decisionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "violation_id")
    ViolationEntity violation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    DecisionType decisionType;

    @Column(nullable = false)
    Double fineAmount;

    @Column(nullable = false)
    String comment;

    @Column(nullable = false)
    String judgeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    DecisionStatus decisionStatus;
}
