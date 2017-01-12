package north.sample.spark.book;

import com.avaje.ebean.Ebean;
import north.sample.domain.Book;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

public class PutBookRoute extends JsonTransformer {

    public PutBookRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long bookId = Long.parseLong(request.params(":id"));
        Book book = Ebean.find(Book.class, bookId);
        if (book != null) {
            String newTitle = request.queryParams("title");
            if (newTitle != null) {
                book.setTitle(newTitle);
            }
            String newAuthor = request.queryParams("author");
            if (newAuthor != null) {
                book.setAuthor(newAuthor);
            }
            Ebean.update(book);
            response.status(204); // 204 No Content
            return "";
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }
    
}
