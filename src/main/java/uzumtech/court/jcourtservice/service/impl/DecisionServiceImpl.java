package uzumtech.court.jcourtservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzumtech.court.jcourtservice.constant.enums.Status;
import uzumtech.court.jcourtservice.entity.Article;
import uzumtech.court.jcourtservice.entity.CourtDecision;
import uzumtech.court.jcourtservice.entity.Violation;
import uzumtech.court.jcourtservice.exception.NotFoundException;
import uzumtech.court.jcourtservice.repository.CourtDecisionRepository;
import uzumtech.court.jcourtservice.repository.ViolationRepository;
import uzumtech.court.jcourtservice.service.abstraction.DecisionService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DecisionServiceImpl implements DecisionService {

    private final CourtDecisionRepository courtDecisionRepository;
    private final ViolationRepository violationRepository;

    @Override
    public CourtDecision makeDecision(UUID violationId){

        Optional<Violation> violation = violationRepository.findById(violationId);

        if(violation.isEmpty()){
            throw new NotFoundException("Violation not found");
        }
        Article article = violation.get().getArticle();

        if(article == null){
            throw new NotFoundException("Article not found");
        }

        CourtDecision decision = CourtDecision.builder()
                .violation(violation.get())
                .fineAmount(article.getFine())
                .decisionType(article.getDecisionType())
                .build();

        violation.get().setStatus(Status.DECIDED);
        violationRepository.save(violation.get());

        return courtDecisionRepository.save(decision);
    }


}
