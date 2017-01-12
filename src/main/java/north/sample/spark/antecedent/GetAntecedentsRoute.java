package north.sample.spark.antecedent;

import com.avaje.ebean.Ebean;
import north.sample.domain.Antecedent;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

public class GetAntecedentsRoute extends JsonTransformer {

    public GetAntecedentsRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        return Ebean.find(Antecedent.class).findList();
    }
    
}
