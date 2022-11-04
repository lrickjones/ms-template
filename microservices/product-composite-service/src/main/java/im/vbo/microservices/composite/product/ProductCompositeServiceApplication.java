package im.vbo.microservices.composite.product;

import im.vbo.microservices.composite.product.services.ProductCompositeIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.boot.actuate.health.StatusAggregator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.emptyList;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@SpringBootApplication
@ComponentScan("im.vbo")
public class ProductCompositeServiceApplication {

    @Value("${api.common.version}")           String apiVersion;
    @Value("${api.common.title}")             String apiTitle;
    @Value("${api.common.description}")       String apiDescription;
    @Value("${api.common.termsOfServiceUrl}") String apiTermsOfServiceUrl;
    @Value("${api.common.license}")           String apiLicense;
    @Value("${api.common.licenseUrl}")        String apiLicenseUrl;
    @Value("${api.common.contact.name}")      String apiContactName;
    @Value("${api.common.contact.url}")       String apiContactUrl;
    @Value("${api.common.contact.email}")     String apiContactEmail;

	/**
	 * Will exposed on $HOST:$PORT/swagger-ui.html
	 *
	 * @return
	 */
	@Bean
	public Docket apiDocumentation() {

		return new Docket(SWAGGER_2)
			.select()
			.apis(basePackage("im.vbo.microservices.composite.product"))
			.paths(PathSelectors.any())
			.build()
				.globalResponseMessage(POST, emptyList())
				.globalResponseMessage(GET, emptyList())
				.globalResponseMessage(DELETE, emptyList())
				.apiInfo(new ApiInfo(
                    apiTitle,
                    apiDescription,
                    apiVersion,
                    apiTermsOfServiceUrl,
                    new Contact(apiContactName, apiContactUrl, apiContactEmail),
                    apiLicense,
                    apiLicenseUrl,
                    emptyList()
                ));
    }

	//TODO: implement health aggregator
	@Autowired
	StatusAggregator healthAggregator;

	@Autowired
	ProductCompositeIntegration integration;

	@Bean
	ReactiveHealthIndicator coreServices() {

		//HealthContributorRegistry registry = new DefaultHealthContributorRegistry(new LinkedHashMap<>());

		//registry.registerContributor("product", integration.getProductHealth());
		//registry.registerContributor("recommendation", () -> integration.getRecommendationHealth());
		//registry.registerContributor("review", () -> integration.getReviewHealth());

		//return new ReactiveHealthIndicator(healthAggregator, registry);
		return null;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}
}
