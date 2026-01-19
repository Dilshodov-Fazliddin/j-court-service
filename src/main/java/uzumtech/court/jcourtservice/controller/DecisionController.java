package uzumtech.court.jcourtservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uzumtech.court.jcourtservice.entity.CourtDecision;
import uzumtech.court.jcourtservice.exception.ApiResponse;
import uzumtech.court.jcourtservice.service.abstraction.DecisionService;

import java.util.UUID;

@RestController
@RequestMapping("/decision")
@RequiredArgsConstructor
public class DecisionController {

    private final DecisionService decisionService;

    @PostMapping("/{violationId}")
    public ResponseEntity<ApiResponse<CourtDecision>> makeDecision (@PathVariable UUID violationId){
        CourtDecision decision = decisionService.makeDecision(violationId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(decision));
    }

}
