package uzumtech.court.jcourtservice.adapter;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;
import uzumtech.court.jcourtservice.dto.request.CourtDecisionRequest;
import uzumtech.court.jcourtservice.dto.request.DecisionRecommendationRequest;
import uzumtech.court.jcourtservice.dto.response.DecisionRecommendationResponse;
import uzumtech.court.jcourtservice.utils.Utils;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GeminiAdapter {

    final RestClient restClient;

    @Value("${gemini.key}")
    String geminiKey;

    @Value("${gemini.url}")
    String geminiUrl;

    final ObjectMapper objectMapper;

    public DecisionRecommendationResponse getRecommendation(
            DecisionRecommendationRequest request
    ) {
        String prompt = Utils.promptMessage(request);
        String body = Utils.body(prompt);

        String responseAi = restClient.post()
                .uri(geminiUrl)
                .headers(headers -> {
                    headers.set("X-Goog-Api-Key", geminiKey);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(body)
                .retrieve()
                .body(String.class);

        String parsedText = Utils.parseAiTextFromGemini(responseAi);
        String cleanText = Utils.extractJson(parsedText);
        return objectMapper.readValue(cleanText, DecisionRecommendationResponse.class);
    }
}
