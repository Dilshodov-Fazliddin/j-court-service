package uzumtech.court.jcourtservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.service.ViolationService;

@RestController
@RequestMapping("/violation")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ViolationController {

    ViolationService violationService;

}
