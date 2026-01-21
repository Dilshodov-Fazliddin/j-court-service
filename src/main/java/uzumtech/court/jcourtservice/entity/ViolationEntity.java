package uzumtech.court.jcourtservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViolationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offender_id")
    OffenderEntity offender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    ArticleEntity article;

    @Column(nullable = false)
    String description;

    @Enumerated(EnumType.STRING)
    ViolationStatus status;
}
