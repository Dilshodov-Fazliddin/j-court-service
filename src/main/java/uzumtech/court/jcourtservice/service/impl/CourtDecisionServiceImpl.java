package uzumtech.court.jcourtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.court.jcourtservice.adapter.GeminiAdapter;
import uzumtech.court.jcourtservice.constant.enums.DecisionStatus;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionRequest;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionUpdateRequest;
import uzumtech.court.jcourtservice.dto.request.DecisionRecommendationRequest;
import uzumtech.court.jcourtservice.dto.response.CourtDecisionResponse;
import uzumtech.court.jcourtservice.dto.response.DecisionRecommendationResponse;
import uzumtech.court.jcourtservice.entity.CourtDecisionEntity;
import uzumtech.court.jcourtservice.entity.ViolationEntity;
import uzumtech.court.jcourtservice.exception.DataNotFoundException;
import uzumtech.court.jcourtservice.mapper.CourtDecisionMapper;
import uzumtech.court.jcourtservice.repository.CourtDecisionRepository;
import uzumtech.court.jcourtservice.repository.ViolationRepository;
import uzumtech.court.jcourtservice.service.CourtDecisionService;
import uzumtech.court.jcourtservice.utils.Utils;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CourtDecisionServiceImpl implements CourtDecisionService {

    CourtDecisionRepository courtDecisionRepository;
    CourtDecisionMapper courtMapper;
    ViolationRepository violationRepository;
    GeminiAdapter geminiAdapter;


    @Override
    public CourtDecisionResponse create(CourtDecisionRequest courtDecisionRequest) {
        var violation = violationRepository.findById(courtDecisionRequest
                .violationId())
                .orElseThrow(() -> new DataNotFoundException("Violation not found with id" + courtDecisionRequest.violationId()));

        var recommendation = geminiAdapter.getRecommendation(DecisionRecommendationRequest.builder().violation(violation).build());

        CourtDecisionEntity courtDecision = buildCourtDecision(courtDecisionRequest, violation, recommendation);

        var saved = courtDecisionRepository.save(courtDecision);

        log.info("Decision created {}", saved);

        return courtMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void updateDecisionStatus(Long id, DecisionStatus decisionStatus) {
        var courtDecision = courtDecisionRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Decision not found with id" + id));

        courtDecision.setDecisionStatus(decisionStatus);

        log.info("Decision status updated with id {} to {}", id, decisionStatus);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateDecision(CourtDecisionUpdateRequest request,Long id) {
        var courtDecision = courtDecisionRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Decision not found with id" + id));


        courtMapper.updateCourtDecisionFromDto(request,courtDecision);

        log.info("Decision updated {} to {}", id,request);
    }

    @Override
    public CourtDecisionResponse getById(Long id) {
        var courtDecision = courtDecisionRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Court not found with id" + id));

        return courtMapper.toResponse(courtDecision);
    }

    private CourtDecisionEntity buildCourtDecision(CourtDecisionRequest courtDecisionRequest, ViolationEntity violation, DecisionRecommendationResponse recommendation){
        var courtDecision = courtMapper.toEntity(courtDecisionRequest);

        courtDecision.setViolation(violation);
        courtDecision.setFineAmount(recommendation.fineAmount());
        courtDecision.setJudgeName(recommendation.judgeName());
        courtDecision.setDecisionType(recommendation.decisionType());
        courtDecision.setComment(recommendation.comment());
        courtDecision.setDecisionNumber(Utils.generateDecisionNumber());

        return courtDecision;
    }
}
