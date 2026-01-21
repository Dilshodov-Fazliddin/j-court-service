package uzumtech.court.jcourtservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.court.jcourtservice.entity.OffenderEntity;

import java.util.List;
import java.util.Optional;

public interface OffenderRepository extends JpaRepository<OffenderEntity, Long> {

    Optional<OffenderEntity> getByPersonalIdentificationNumber(String personalIdentificationNumber);
}
