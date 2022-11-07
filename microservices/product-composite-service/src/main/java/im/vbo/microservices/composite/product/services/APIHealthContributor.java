package im.vbo.microservices.composite.product.services;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * API Health Contributor
 *
 * Adds endpoints for service api health tests to actuator/health
 *
 */
@Component("APIHealth")
public class APIHealthContributor implements CompositeHealthContributor {
    private Map<String, HealthContributor> contributors = new LinkedHashMap<>();

    public APIHealthContributor(ProductServiceHealthIndicator productServiceHealthIndicator,
                                RecommendationServiceHealthIndicator recommendationServiceHealthIndicator,
                                ReviewServiceHealthIndicator reviewServiceHealthIndicator) {
        contributors.put("productService", productServiceHealthIndicator);
        contributors.put("recommendationService", recommendationServiceHealthIndicator);
        contributors.put("reviewService", reviewServiceHealthIndicator);
    }

    @Override
    public HealthContributor getContributor(String name) {
        return contributors.get(name);
    }

    @Override
    public Iterator<NamedContributor<HealthContributor>> iterator() {
        return contributors.entrySet().stream()
                .map((entry) ->
                        NamedContributor.of(entry.getKey(),
                                entry.getValue())).iterator();
    }

}
