package im.vbo.microservices.composite.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RecommendationServiceHealthIndicator extends APIHealthIndicator implements HealthIndicator  {

    @Autowired
    public RecommendationServiceHealthIndicator(
            WebClient.Builder webClient,
            @Value("${app.recommendation-service.host}") String recommendationServiceHost,
            @Value("${app.recommendation-service.port}") int recommendationServicePort

    ) {
        super(webClient, "http://" + recommendationServiceHost + ":" + recommendationServicePort);
    }

    @Override
    public Health health() {
       return callAPIHealth();
    }

}
