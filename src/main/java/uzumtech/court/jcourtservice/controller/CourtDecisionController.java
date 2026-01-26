package uzumtech.court.jcourtservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.constant.enums.DecisionStatus;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionRequest;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.CourtDecisionResponse;
import uzumtech.court.jcourtservice.service.CourtDecisionService;

@RestController
@RequestMapping("/api/court/decision")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CourtDecisionController {

    CourtDecisionService courtDecisionService;

    @PostMapping
    public CourtDecisionResponse create(
            @RequestBody CourtDecisionRequest courtDecisionRequest
    ){
        return courtDecisionService.create(courtDecisionRequest);
    }


    @PatchMapping("/status/{id}")
    public void updateDecisionStatus(
            @PathVariable Long id,
            @RequestParam DecisionStatus decisionStatus
    ) {
        courtDecisionService.updateDecisionStatus(id, decisionStatus);
    }

    @PutMapping("/{id}")
    public void updateDecision(
            @PathVariable Long id,
            @RequestBody CourtDecisionUpdateRequest request
    ) {
        courtDecisionService.updateDecision(request, id);
    }

    @GetMapping("/{id}")
    public CourtDecisionResponse getById(@PathVariable Long id) {
        return courtDecisionService.getById(id);
    }
}
