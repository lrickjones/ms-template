package im.vbo.microservices.composite.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ReviewServiceHealthIndicator extends APIHealthIndicator implements HealthIndicator  {

    @Autowired
    public ReviewServiceHealthIndicator(
            WebClient.Builder webClient,
            @Value("${app.review-service.host}") String reviewServiceHost,
            @Value("${app.review-service.port}") int reviewServicePort

    ) {
        super(webClient, "http://" + reviewServiceHost + ":" + reviewServicePort);
    }

    @Override
    public Health health() {
       return callAPIHealth();
    }

}
