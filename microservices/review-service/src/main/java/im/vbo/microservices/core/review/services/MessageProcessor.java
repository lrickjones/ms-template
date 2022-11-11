package im.vbo.microservices.core.review.services;

import im.vbo.api.core.review.Review;
import im.vbo.api.core.review.ReviewService;
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

    private final ReviewService reviewService;

    @Autowired
    public MessageProcessor(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @StreamListener(target = Sink.INPUT)
    public void process(Event<Integer, Review> event) {

        log.info("Process message created at {}...", event.getEventCreatedAt());

        switch (event.getEventType()) {

        case CREATE:
            Review review = event.getData();
            log.info("Create review with ID: {}/{}", review.getProductId(), review.getReviewId());
            reviewService.createReview(review);
            break;

        case DELETE:
            int productId = event.getKey();
            log.info("Delete reviews with ProductID: {}", productId);
            reviewService.deleteReviews(productId);
            break;

        default:
            String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
            log.warn(errorMessage);
            throw new EventProcessingException(errorMessage);
        }

        log.info("Message processing done!");
    }
}
