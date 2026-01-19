package uzumtech.court.jcourtservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.dto.request.OffenderDto;
import uzumtech.court.jcourtservice.entity.Offender;
import uzumtech.court.jcourtservice.exception.ApiResponse;
import uzumtech.court.jcourtservice.service.abstraction.OffenderService;

@RestController
@RequestMapping("/offender")
@RequiredArgsConstructor
public class OffenderController {

    private final OffenderService offenderService;

    @PostMapping()
    public ResponseEntity<ApiResponse<Offender>> addOffender(@RequestBody OffenderDto offenderDto) {
        Offender offender = offenderService.createOffender(offenderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(offender));
    }

}
