package im.vbo.microservices.core.recommendation.services;

import im.vbo.api.core.recommendation.Recommendation;
import im.vbo.api.core.recommendation.RecommendationService;
import im.vbo.api.event.Event;
import im.vbo.office.util.exceptions.EventProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
public class MessageProcessor {

    private final RecommendationService recommendationService;

    @Autowired
    public MessageProcessor(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @StreamListener(target = Sink.INPUT)
    public void process(Event<Integer, Recommendation> event) {

        log.info("Process message created at {}...", event.getEventCreatedAt());

        switch (event.getEventType()) {

        case CREATE:
            Recommendation recommendation = event.getData();
            log.info("Create recommendation with ID: {}/{}", recommendation.getProductId(), recommendation.getRecommendationId());
            recommendationService.createRecommendation(recommendation);
            break;

        case DELETE:
            int productId = event.getKey();
            log.info("Delete recommendations with ProductID: {}", productId);
            recommendationService.deleteRecommendations(productId);
            break;

        default:
            String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
            log.warn(errorMessage);
            throw new EventProcessingException(errorMessage);
        }

        log.info("Message processing done!");
    }
}
