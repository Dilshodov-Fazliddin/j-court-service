package uzumtech.court.jcourtservice.service;

import uzumtech.court.jcourtservice.constant.enums.DecisionStatus;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionRequest;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.CourtDecisionResponse;

public interface CourtDecisionService {
    CourtDecisionResponse create(CourtDecisionRequest courtDecisionRequest);
    void updateDecisionStatus(Long id, DecisionStatus decisionStatus);
    void updateDecision(CourtDecisionUpdateRequest request,Long id);
    CourtDecisionResponse getById(Long id);
}
