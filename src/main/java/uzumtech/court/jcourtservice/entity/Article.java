package uzumtech.court.jcourtservice.entity;

import jakarta.persistence.*;
import lombok.*;
import uzumtech.court.jcourtservice.constant.enums.DecisionType;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article extends BaseEntity {

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double fine;

    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;



}
