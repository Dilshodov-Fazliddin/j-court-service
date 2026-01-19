package uzumtech.court.jcourtservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzumtech.court.jcourtservice.dto.request.OffenderDto;
import uzumtech.court.jcourtservice.entity.Offender;
import uzumtech.court.jcourtservice.exception.ConflictException;
import uzumtech.court.jcourtservice.repository.OffenderRepository;
import uzumtech.court.jcourtservice.service.abstraction.OffenderService;
;import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OffenderServiceImpl implements OffenderService {

    private final OffenderRepository offenderRepository;

    @Override
    public Offender createOffender(OffenderDto offenderDto) {

        Optional<Offender> existOffender = offenderRepository.findByPassportNumber(offenderDto.getPassportNumber());
        if(existOffender.isPresent()){
            throw new ConflictException("Offender already exists");
        }

        Offender offender = Offender.builder()
                .fullName(offenderDto.getFullName())
                .passportNumber(offenderDto.getPassportNumber())
                .birthDate(offenderDto.getDateOfBirth())
                .build();

        return offenderRepository.save(offender);
    }


}
