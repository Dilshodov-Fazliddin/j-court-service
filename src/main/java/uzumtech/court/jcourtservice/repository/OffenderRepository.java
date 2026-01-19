package uzumtech.court.jcourtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzumtech.court.jcourtservice.entity.Offender;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OffenderRepository extends JpaRepository<Offender, UUID> {
    Offender getOffendersById(UUID id);

    Optional<Offender> findByPassportNumber(String passportNumber);
}
