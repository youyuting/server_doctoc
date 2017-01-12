package north.sample.spark.antecedent;

import com.avaje.ebean.Ebean;
import north.sample.domain.Antecedent;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class PostAntecedentRoute extends JsonTransformer {

    public PostAntecedentRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        Antecedent antecedent = null;
		try {
			antecedent = mapper.readValue(request.body(), Antecedent.class);
		} catch (IOException e) {
			
			response.status(500); // Server-side error
			
			return createErrorResponse("Antecedent couldn't be saved");
		}
        Ebean.save(antecedent);
        response.status(201); // 201 Created
        return antecedent;
    }
}
