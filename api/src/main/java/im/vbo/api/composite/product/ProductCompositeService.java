package im.vbo.api.composite.product;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import io.swagger.v3.oas.annotations.Operation;

public interface ProductCompositeService {

    /**
     * Sample usage:
     * <p>
     * curl -X POST $HOST:$PORT/product-composite \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"name":"product 123","weight":123}'
     *
     * @param body
     */
    @Operation(summary="${api.product-composite.create-composite-product.description}")
    @PostMapping(
        value    = "/product-composite",
        consumes = "application/json")
    void createCompositeProduct(@RequestBody ProductAggregate body);

    /**
     * Sample usage: curl $HOST:$PORT/product-composite/1
     * <p>
     * @param productId
     * @return the composite product info, if found, else null
     */
    @Operation(summary="${api.product-composite.get-composite-product.description}")
    @GetMapping(
        value    = "/product-composite/{productId}",
        produces = "application/json")
    Mono<ProductAggregate> getCompositeProduct(@PathVariable int productId);


    /**
     * Sample usage:
     * <p>
     * curl -X DELETE $HOST:$PORT/product-composite/1
     *
     * @param productId
     */
    @Operation(summary="${api.product-composite.delete-composite-product.description}")
    @DeleteMapping(value = "/product-composite/{productId}")
    void deleteCompositeProduct(@PathVariable int productId);
}