package north.sample.spark.book;

import com.avaje.ebean.Ebean;
import north.sample.domain.Book;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

public class GetBookRoute extends JsonTransformer {

    public GetBookRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long bookId = Long.parseLong(request.params(":id"));
        Book book = Ebean.find(Book.class, bookId);
        if (book != null) {
            return book;
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }
}
