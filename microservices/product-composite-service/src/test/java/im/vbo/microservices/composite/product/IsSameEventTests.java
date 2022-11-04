package im.vbo.microservices.composite.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.vbo.api.core.product.Product;
import im.vbo.api.event.Event;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static im.vbo.api.event.Event.Type.CREATE;
import static im.vbo.api.event.Event.Type.DELETE;
import static im.vbo.microservices.composite.product.IsSameEvent.sameEventExceptCreatedAt;

public class IsSameEventTests {



    @Test
    public void testEventObjectCompare() throws JsonProcessingException {
		ObjectMapper mapper;
    	// Event #1 and #2 are the same event, but occurs as different times
		// Event #3 and #4 are different events
		Event<Integer, Product> event1 = new Event<>(CREATE, 1, new Product(1, "name", 1, null));
		Event<Integer, Product> event2 = new Event<>(CREATE, 1, new Product(1, "name", 1, null));
		Event<Integer, Product> event3 = new Event<>(DELETE, 1, null);
		Event<Integer, Product> event4 = new Event<>(CREATE, 1, new Product(2, "name", 1, null));

		mapper = new ObjectMapper();
		String event1JSon = mapper.writeValueAsString(event1);

		assertThat(event1JSon, is(sameEventExceptCreatedAt(event2)));
		assertThat(event1JSon, not(sameEventExceptCreatedAt(event3)));
		assertThat(event1JSon, not(sameEventExceptCreatedAt(event4)));
    }
}
