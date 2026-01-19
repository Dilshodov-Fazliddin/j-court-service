package uzumtech.court.jcourtservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.dto.request.ViolationDto;
import uzumtech.court.jcourtservice.entity.Violation;
import uzumtech.court.jcourtservice.exception.ApiException;
import uzumtech.court.jcourtservice.exception.ApiResponse;
import uzumtech.court.jcourtservice.service.abstraction.ViolationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/violation")
@RequiredArgsConstructor
public class ViolationController {

    private final ViolationService violationService;

    @PostMapping
    public ResponseEntity<ApiResponse<Violation>> registerViolation(@RequestBody ViolationDto violationDto) {
        Violation violation = violationService.registerViolation(violationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(violation));
    }

    @PostMapping("/{violationId}")
    public ResponseEntity<ApiResponse<Violation>> violationSent(@PathVariable UUID violationId) {
        Violation violation = violationService.violationSent(violationId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(violation));
    }

    @GetMapping("/{offenderPassportNumber}")
    public ResponseEntity<ApiResponse<List<Violation>>> getOffenderViolations(@PathVariable String offenderPassportNumber) {
        List<Violation> allViolations = violationService.getOffenderViolations(offenderPassportNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(allViolations));
    }
}
