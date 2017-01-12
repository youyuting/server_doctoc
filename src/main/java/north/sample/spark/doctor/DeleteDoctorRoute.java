package north.sample.spark.doctor;

import com.avaje.ebean.Ebean;
import north.sample.domain.Doctor;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

/**
 * Created by yyou on 2016/11/17.
 */
public class DeleteDoctorRoute  extends JsonTransformer {

    public DeleteDoctorRoute(String path) {
        super(path);
    }
    @Override
    public Object handle(Request request, Response response) {
        long doctorId = Long.parseLong(request.params(":id"));
        Doctor doctor = Ebean.find(Doctor.class, doctorId);
        if (doctor != null) {
            Ebean.delete(doctor);
            response.status(204); // 204 No Content
            return "";
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }
}
