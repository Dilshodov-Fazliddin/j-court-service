package uzumtech.court.jcourtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.court.jcourtservice.entity.CourtDecisionEntity;

public interface CourtDecisionRepository extends JpaRepository<CourtDecisionEntity,Long> {
}
