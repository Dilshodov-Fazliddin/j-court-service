package uzumtech.court.jcourtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.court.jcourtservice.entity.ViolationEntity;

public interface ViolationRepository extends JpaRepository<ViolationEntity, Long> {
}
