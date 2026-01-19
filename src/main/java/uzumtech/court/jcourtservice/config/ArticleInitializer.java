package uzumtech.court.jcourtservice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uzumtech.court.jcourtservice.entity.Article;
import uzumtech.court.jcourtservice.repository.ArticleRepository;

import java.util.List;

import static uzumtech.court.jcourtservice.constant.enums.DecisionType.*;

@Component
@RequiredArgsConstructor
public class ArticleInitializer {

    private final ArticleRepository articleRepository;

    @PostConstruct
    public void init() {
        if (articleRepository.count() > 0) {
            return;
        }

        List<Article> violationTypes = List.of(
                Article.builder()
                        .code("12.5")
                        .title("Превышение скорости")
                        .description("Превышение установленного скоростного режима")
                        .fine(500_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.8")
                        .title("Вождение в нетрезвом виде")
                        .description("Управление ТС в состоянии алкогольного опьянения")
                        .fine(2_000_000)
                        .decisionType(ARREST)
                        .build(),

                Article.builder()
                        .code("12.12")
                        .title("Проезд на запрещающий сигнал светофора")
                        .description("Проезд перекрёстка на красный сигнал светофора.")
                        .fine(700_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.15")
                        .title("Выезд на встречную полосу")
                        .description("Выезд на полосу встречного движения с нарушением ПДД.")
                        .fine(3_00_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.16")
                        .title("Нарушение правил дорожных знаков и разметки")
                        .description("Игнорирование дорожных знаков или разметки.")
                        .fine(400_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.18")
                        .title("Непредоставление преимущества пешеходу")
                        .description("Водитель не уступил дорогу пешеходу на переходе.")
                        .fine(800_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.19")
                        .title("Нарушение правил остановки и стоянки")
                        .description("Остановка или стоянка в запрещённом месте.")
                        .fine(200_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.24")
                        .title("Нарушение правил перевозки пассажиров")
                        .description("Перевозка пассажиров с нарушением установленных норм.")
                        .fine(600_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.26")
                        .title("Отказ от прохождения медицинского освидетельствования")
                        .description("Отказ водителя от медицинского освидетельствования.")
                        .fine(3_000_000)
                        .decisionType(FINE)
                        .build(),

                Article.builder()
                        .code("12.27")
                        .title("Оставление места ДТП")
                        .description("Покидание места дорожно-транспортного происшествия.")
                        .fine(700_000)
                        .decisionType(FINE)
                        .build()
        );

        articleRepository.saveAll(violationTypes);
    }
}

