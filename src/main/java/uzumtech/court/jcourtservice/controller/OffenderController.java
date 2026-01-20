package uzumtech.court.jcourtservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.response.OffenderResponse;
import uzumtech.court.jcourtservice.service.OffenderService;

@RestController
@RequestMapping("/api/court/offender")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)

public class OffenderController {

    OffenderService offenderService;



    @PostMapping
    public OffenderResponse createOffender(
            @RequestBody @Valid OffenderRequest offenderRequest
    ){
        return offenderService.create(offenderRequest);
    }
}
