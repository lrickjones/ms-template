package im.vbo.microservices.composite.product.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * APIHealthIndicator
 *
 * Abstract class to support common functions for service health indicators
 *
 */
@Slf4j
public abstract class APIHealthIndicator {
    WebClient webClient;
    String url;

    @Autowired
    public APIHealthIndicator(WebClient.Builder webClient, String url) {
        this.webClient = webClient.build();
        this.url = url;
    }

    protected Health callAPIHealth() {
        url += "/actuator/health";
        //log.debug("Will call the Health API on URL: {}", url);
        return webClient.get().uri(url).retrieve().bodyToMono(String.class)
                .map(s -> new Health.Builder().up().build())
                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()))
                .log()
                .block();
    }
}
