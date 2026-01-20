package uzumtech.court.jcourtservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uzumtech.court.jcourtservice.service.CourtDecisionService;

@RestController
@RequestMapping("/decision")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CourtDecisionController {

    CourtDecisionService courtDecisionService;
}
