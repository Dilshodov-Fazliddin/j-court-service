package uzumtech.court.jcourtservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViolationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offender_id")
    OffenderEntity offender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    ArticleEntity article;

    @Column(nullable = false)
    String description;

    @Enumerated(EnumType.STRING)
    ViolationStatus status;

    @Column(nullable = false)
    LocalDateTime offenseTime;
}
