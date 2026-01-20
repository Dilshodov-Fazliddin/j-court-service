package uzumtech.court.jcourtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzumtech.court.jcourtservice.entity.OffenderEntity;

import java.util.UUID;

public interface OffenderRepository extends JpaRepository<OffenderEntity, Long> {
}
