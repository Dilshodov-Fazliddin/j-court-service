package uzumtech.court.jcourtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzumtech.court.jcourtservice.entity.CourtDecision;

import java.util.UUID;

@Repository
public interface CourtDecisionRepository extends JpaRepository<CourtDecision, UUID> {
}
