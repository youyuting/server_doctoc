package north.sample.spark.book;

import com.avaje.ebean.Ebean;
import north.sample.domain.Book;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

public class GetBooksRoute extends JsonTransformer {

    public GetBooksRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        return Ebean.find(Book.class).findList();
    }
    
}
