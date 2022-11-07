package im.vbo.microservices.composite.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductServiceHealthIndicator extends APIHealthIndicator implements HealthIndicator  {

    @Autowired
    public ProductServiceHealthIndicator(
            WebClient.Builder webClient,
            @Value("${app.product-service.host}") String productServiceHost,
            @Value("${app.product-service.port}") int productServicePort

    ) {
        super(webClient, "http://" + productServiceHost + ":" + productServicePort);
    }

    @Override
    public Health health() {
       return callAPIHealth();
    }

}
