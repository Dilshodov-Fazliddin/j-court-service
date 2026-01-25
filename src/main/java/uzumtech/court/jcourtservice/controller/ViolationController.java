package uzumtech.court.jcourtservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.dto.request.ViolationRequest;
import uzumtech.court.jcourtservice.dto.request.ViolationUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ViolationResponse;
import uzumtech.court.jcourtservice.service.ViolationService;

@RestController
@RequestMapping("/api/court/violations")
@FieldDefaults(makeFinal = true,level = AccessLevel.PUBLIC)
@RequiredArgsConstructor
public class ViolationController {

    ViolationService violationService;

    @PostMapping
    public ViolationResponse create(@RequestBody @Valid ViolationRequest violationRequest) {
        return violationService.create(violationRequest);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody ViolationUpdateRequest violationRequest
    ) {
        violationService.updateById(violationRequest, id);
    }

    @GetMapping
    public Page<ViolationResponse> getAll(Pageable pageable) {
        return violationService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ViolationResponse getById(@PathVariable Long id) {
        return violationService.getById(id);
    }
}
