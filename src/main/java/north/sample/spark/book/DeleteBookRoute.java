package north.sample.spark.book;

import com.avaje.ebean.Ebean;
import north.sample.domain.Book;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

public class DeleteBookRoute extends JsonTransformer {

    public DeleteBookRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long bookId = Long.parseLong(request.params(":id"));
        Book book = Ebean.find(Book.class, bookId);
        if (book != null) {
            Ebean.delete(book);
            response.status(204); // 204 No Content
            return "";
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }

}
