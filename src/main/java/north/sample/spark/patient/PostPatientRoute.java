package north.sample.spark.patient;

import com.avaje.ebean.Ebean;
import north.sample.domain.Patient;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yyou on 2016/11/14.
 */
public class PostPatientRoute extends JsonTransformer {
    public PostPatientRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        Patient patient = null;
        try {
            patient = mapper.readValue(request.body(), Patient.class);
            byte[] encodedPassword = MessageDigest.getInstance("SHA-256").digest(patient.getPassword().getBytes());
            patient.setPassword(new String(encodedPassword));
            Ebean.save(patient);
            response.status(201); // 201 Created
            return patient;
        } catch (IOException | NoSuchAlgorithmException e) {

            response.status(500); // Server-side error
            return createErrorResponse("Patient couldn't be saved error was " + e.getMessage());
        }

    }


}
