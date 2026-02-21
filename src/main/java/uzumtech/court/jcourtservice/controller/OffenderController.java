package uzumtech.court.jcourtservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.request.OffenderUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;
import uzumtech.court.jcourtservice.service.OffenderService;

@RestController
@RequestMapping("/api/court/offender")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)

public class OffenderController {

    OffenderService offenderService;

    @GetMapping
    public Page<OffenderResponse> getAllOffenders(Pageable pageable) {
        return offenderService.getAllOffenders(pageable);
    }

    @GetMapping("/personal-number/{personalNumber}")
    public OffenderResponse getByPersonalNumber(
            @PathVariable String personalNumber) {

        return offenderService
                .getOffenderByPersonalIdentificationNumber(personalNumber);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody @Valid OffenderUpdateRequest offenderRequest) {

        offenderService.update(id, offenderRequest);
    }
 }
