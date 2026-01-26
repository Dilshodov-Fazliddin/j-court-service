package uzumtech.court.jcourtservice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import uzumtech.court.jcourtservice.dto.request.DecisionRecommendationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDate;
import java.util.UUID;

public final class Utils {

    public static String promptMessage(DecisionRecommendationRequest request) {
        return """
    Представь, что ты судья. Проанализируй данные:
    Имя: %s %s, Возраст: %d, Адрес: %s
    Статья УК: %s.
    О чем статья: %s
    Штраф должен быть: %s
    
    ЗАДАНИЕ:
    Вынеси решение СТРОГО в формате JSON без Markdown-разметки (без ```json).
    коменты на русском.
    
    Обязан:
    можешь изменить decisionType.
    Ты обязан всем дать штраф.
    
    
    МНЕ НУЖЕН БЫСТРЫЙ И КОРОТКИЙ ОТВЕТ
    СХЕМА:
    {
      "decisionType": "WARNING/FINE/COMMUNITY_SERVICE/ARREST",
      "comment": "строка",
      "judgeName": "строка",
      "fineAmount": 0.0
    }
    
    """.formatted(
                request.violation().getOffender().getName(),
                request.violation().getOffender().getSurname(),
                request.violation().getOffender().getAge(),
                request.violation().getOffender().getAddress(),
                request.violation().getArticle().getCode(),
                request.violation().getArticle().getTitle(),
                request.violation().getArticle().getFine()
        );
    }

    public static String body(String prompt) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode root = mapper.createObjectNode();
            root.putArray("contents")
                    .addObject()
                    .putArray("parts")
                    .addObject()
                    .put("text", prompt);

            return mapper.writeValueAsString(root);
        } catch (Exception e) {
            return "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt.replace("\"", "\\\"").replace("\n", "\\n") + "\"}]}]}";
        }
    }

    public static String extractJson(String rawResponse) {
        int firstBrace = rawResponse.indexOf("{");
        int lastBrace = rawResponse.lastIndexOf("}");

        if (firstBrace == -1 || lastBrace == -1) {
            throw new RuntimeException("В ответе от ИИ нет валидного JSON: " + rawResponse);
        }

        return rawResponse.substring(firstBrace, lastBrace + 1);
    }

    public static String parseAiTextFromGemini(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);

            return root.at("/candidates/0/content/parts/0/text").asText();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось извлечь текст из ответа Google API. Проверьте структуру JSON.", e);
        }
    }


    public static String generateDecisionNumber() {
        int year = LocalDate.now().getYear();
        String random = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        return year + "-UZ-" + random;
    }

}