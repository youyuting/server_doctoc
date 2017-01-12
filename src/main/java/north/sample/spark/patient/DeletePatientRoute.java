package north.sample.spark.patient;

import com.avaje.ebean.Ebean;
import north.sample.domain.Patient;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

/**
 * Created by yyou on 2016/12/6.
 */
public class DeletePatientRoute  extends JsonTransformer {

    public DeletePatientRoute(String path) {
        super(path);
    }
    @Override
    public Object handle(Request request, Response response) {
        long patientId = Long.parseLong(request.params(":id"));
        Patient patient = Ebean.find(Patient.class, patientId);
        if (patient != null) {
            Ebean.delete(patient);
            response.status(204); // 204 No Content
            return "";
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }

}
