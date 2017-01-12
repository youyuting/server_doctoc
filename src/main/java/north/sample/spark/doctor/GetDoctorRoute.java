package north.sample.spark.doctor;

import com.avaje.ebean.Ebean;
import north.sample.domain.Book;
import north.sample.domain.Doctor;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

/**
 * Created by yyou on 2016/11/14.
 */
public class GetDoctorRoute extends JsonTransformer{
    public GetDoctorRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long doctorId = Long.parseLong(request.params(":id"));
        Doctor doctor = Ebean.find(Doctor.class, doctorId);
        if (doctor != null) {
            return doctor;
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }
}
