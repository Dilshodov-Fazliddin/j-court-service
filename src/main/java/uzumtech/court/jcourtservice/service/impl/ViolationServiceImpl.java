package uzumtech.court.jcourtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.court.jcourtservice.constant.enums.ViolationStatus;
import uzumtech.court.jcourtservice.dto.request.OffenderRequest;
import uzumtech.court.jcourtservice.dto.request.ViolationRequest;
import uzumtech.court.jcourtservice.dto.request.ViolationUpdateRequest;
import uzumtech.court.jcourtservice.dto.response.ViolationResponse;
import uzumtech.court.jcourtservice.entity.OffenderEntity;
import uzumtech.court.jcourtservice.entity.ViolationEntity;
import uzumtech.court.jcourtservice.exception.DataNotFoundException;
import uzumtech.court.jcourtservice.mapper.ViolationMapper;
import uzumtech.court.jcourtservice.repository.ArticleRepository;
import uzumtech.court.jcourtservice.repository.OffenderRepository;
import uzumtech.court.jcourtservice.repository.ViolationRepository;
import uzumtech.court.jcourtservice.service.OffenderService;
import uzumtech.court.jcourtservice.service.ViolationService;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ViolationServiceImpl implements ViolationService {

    ViolationMapper violationMapper;
    ViolationRepository violationRepository;
    ArticleRepository articleRepository;
    OffenderService offenderService;

    @Override
    @Transactional
    public ViolationResponse create(ViolationRequest violationRequest) {
        var violation = violationMapper.toEntity(violationRequest);

        var article= articleRepository
                .findById(violationRequest.articleId())
                .orElseThrow(()->new DataNotFoundException("Article not found with id"+violationRequest.articleId()));


        var offender = offenderService.create(OffenderRequest.builder()
                .personalIdentificationNumber(violationRequest.personalIdentificationNumber())
                .articleId(violationRequest.articleId()).build());


        violation.setOffender(offender);
        violation.setArticle(article);
        var saved = violationRepository.save(violation);

        return violationMapper.toResponse(saved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateById(ViolationUpdateRequest violationRequest, Long id) {
        var violation = violationRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Violation not found with id:" + id));

        violationMapper.updateViolationFromDto(violationRequest,violation);

        log.info("violation updated with id {} to {}", id, violationRequest);
    }

    @Override
    public Page<ViolationResponse> getAll(Pageable pageable) {
        var violationEntities = violationRepository.findAll(pageable);
        return violationEntities.map(violationMapper::toResponse);
    }

    @Override
    public ViolationResponse getById(Long id) {
        var violation = violationRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Violation not found with id:" + id));

        return violationMapper.toResponse(violation);

    }
}
