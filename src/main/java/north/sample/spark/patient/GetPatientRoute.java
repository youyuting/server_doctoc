package north.sample.spark.patient;

import com.avaje.ebean.Ebean;
import north.sample.domain.Patient;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

/**
 * Created by yyou on 2016/11/14.
 */
public class GetPatientRoute extends JsonTransformer {
    public GetPatientRoute(String path) {
        super(path);
    }
    @Override

    public Object handle(Request request, Response response) {
        long patientId = Long.parseLong(request.params(":id"));
        Patient patient = Ebean.find(Patient.class, patientId);
        if (patient != null) {
            return patient;
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }

}
