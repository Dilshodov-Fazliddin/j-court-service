package uzumtech.court.jcourtservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzumtech.court.jcourtservice.constant.enums.Status;
import uzumtech.court.jcourtservice.dto.request.OffenderDto;
import uzumtech.court.jcourtservice.dto.request.ViolationDto;
import uzumtech.court.jcourtservice.entity.Article;
import uzumtech.court.jcourtservice.entity.Offender;
import uzumtech.court.jcourtservice.entity.Violation;
import uzumtech.court.jcourtservice.exception.NotFoundException;
import uzumtech.court.jcourtservice.repository.ArticleRepository;
import uzumtech.court.jcourtservice.repository.OffenderRepository;
import uzumtech.court.jcourtservice.repository.ViolationRepository;
import uzumtech.court.jcourtservice.service.abstraction.ViolationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ViolationServiceImpl implements ViolationService {

    private final ViolationRepository violationRepository;
    private final ArticleRepository articleRepository;
    private final OffenderRepository offenderRepository;

    @Override
    public Violation registerViolation(ViolationDto violationDto) {

        Optional<Article> article = articleRepository.findByCode(violationDto.getArticleCode());
        if(article.isEmpty()){
            throw new NotFoundException("Article not found");
        }

        Optional< Offender> offender = offenderRepository.findByPassportNumber(violationDto.getOffenderPassportNumber());
        if(offender.isEmpty()){
            throw new NotFoundException("Offender not found");
        }



        Violation violation = Violation.builder()
                .article(article.get())
                .offender(offender.get())
                .description(article.get().getDescription())
                .status(Status.REGISTERED)
                .build();

        return violationRepository.save(violation);

    }

    @Override
    public Violation violationSent(UUID violationId) {
        Optional<Violation> violation = violationRepository.findById(violationId);

        if(violation.isEmpty()) {
            throw new NotFoundException("Violation not found");
        }
        violation.get().setStatus(Status.SENT);
        return violationRepository.save(violation.get());
    }

    @Override
    public List<Violation> getOffenderViolations(String passportNumber) {
        Optional<Offender> offender = offenderRepository.findByPassportNumber(passportNumber);
        if(offender.isEmpty()) {
            throw new NotFoundException("Offender not found");
        }

        return violationRepository.findAll().stream()
                .filter(violation -> violation.getOffender()
                        .getPassportNumber().equals(offender.get().getPassportNumber()))
                .toList();
    }


}
