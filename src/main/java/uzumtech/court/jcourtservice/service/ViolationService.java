package uzumtech.court.jcourtservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.court.jcourtservice.dto.request.ViolationRequest;
import uzumtech.court.jcourtservice.dto.request.ViolationUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ViolationResponse;
import uzumtech.court.jcourtservice.entity.ViolationEntity;

public interface ViolationService {
    ViolationResponse create(ViolationRequest violationRequest);
    void updateById(ViolationUpdateRequest violationRequest, Long id);
    Page<ViolationResponse> getAll(Pageable pageable);
   ViolationResponse getById(Long id);
}
