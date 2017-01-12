package north.sample.spark.book;

import java.io.IOException;

import north.sample.domain.Book;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

import com.avaje.ebean.Ebean;

public class PostBookRoute extends JsonTransformer {

    public PostBookRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        Book book = null;
		try {
			book = mapper.readValue(request.body(), Book.class);
		} catch (IOException e) {
			
			response.status(500); // Server-side error
			
			return createErrorResponse("Book couldn't be saved");
		}
        Ebean.save(book);
        response.status(201); // 201 Created
        return book;
    }
}
