package north.sample.spark.antecedent;

import com.avaje.ebean.Ebean;
import north.sample.domain.Antecedent;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

public class GetAntecedentRoute extends JsonTransformer {

    public GetAntecedentRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long antecedentId = Long.parseLong(request.params(":id"));
        Antecedent antecedent = Ebean.find(Antecedent.class, antecedentId);
        if (antecedent != null) {
            return antecedent;
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }
}
