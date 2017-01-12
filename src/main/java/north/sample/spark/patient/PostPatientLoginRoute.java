package north.sample.spark.patient;

import com.avaje.ebean.Ebean;
import north.sample.domain.Patient;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yyou on 2016/11/14.
 */
public class PostPatientLoginRoute extends JsonTransformer {
    public PostPatientLoginRoute(String path) {
        super(path);
    }
    @Override

    public Object handle(Request request, Response response) {
        if (request.queryParams("email") != null && !request.queryParams("email").isEmpty() && request.queryParams("password") != null && !request.queryParams("password").isEmpty()) {

            Patient patient = Patient.findByEmail(request.queryParams("email"));
            if (patient != null) {
                try {
                    byte[] encodedPassword = MessageDigest.getInstance("SHA-256").digest(request.queryParams("password").getBytes());
                    if (patient.getPassword().equals(new String(encodedPassword))) {
                        request.session().attribute("user", patient);
                        return patient;
                    } else {
                        response.status(401);
                        return createErrorResponse("Password not right");
                    }
                } catch (NoSuchAlgorithmException e) {
                    response.status(500); // 500 internal error
                    return createErrorResponse(e.getMessage());
                }
            } else {
                response.status(404); // 404 Not found
                return createErrorResponse("Not found");
            }
        } else {
            response.status(400); // 400 Bad request
            return createErrorResponse("Bad request !");
        }
    }

}
