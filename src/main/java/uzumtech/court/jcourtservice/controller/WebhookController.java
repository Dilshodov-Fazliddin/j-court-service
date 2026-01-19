package uzumtech.court.jcourtservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzumtech.court.jcourtservice.entity.WebhookSubscription;
import uzumtech.court.jcourtservice.exception.ApiResponse;
import uzumtech.court.jcourtservice.service.abstraction.WebhookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService WebhookService;

    @PostMapping
    public ResponseEntity<ApiResponse<WebhookSubscription>> create(
            @RequestBody String url
    ) {
        WebhookSubscription subscription =
                WebhookService.create(url);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(subscription));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WebhookSubscription>>> getAll() {
        List<WebhookSubscription> subscriptions =
                WebhookService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(subscriptions));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<WebhookSubscription>> activate(
            @PathVariable UUID id
    ) {
        WebhookSubscription subscription =
                WebhookService.activate(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(subscription));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<WebhookSubscription>> deactivate(
            @PathVariable UUID id
    ) {
        WebhookSubscription subscription =
                WebhookService.deactivate(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(subscription));
    }
}
