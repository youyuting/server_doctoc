package north.sample.spark.patient;

import com.avaje.ebean.Ebean;
import north.sample.domain.Patient;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

/**
 * Created by yyou on 2016/12/6.
 */
public class PutPatientRoute extends JsonTransformer {

    public PutPatientRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long patientId = Long.parseLong(request.params(":id"));
        Patient patient = Ebean.find(Patient.class, patientId);
        if (patient != null) {
            //password
            String newPassword = request.queryParams("password");
            if (newPassword != null) {
                patient.setPassword(newPassword);
            }
            
            //lastName
            String newLastName = request.queryParams("lastName");
            if (newLastName != null) {
                patient.setLastName(newLastName);
            }

            //firstName
            String newFirstName = request.queryParams("firstName");
            if (newFirstName != null) {
                patient.setLastName(newFirstName);
            }
            
            //address
            String newAddress = request.queryParams("address");
            if (newAddress != null) {
                patient.setAddress(newAddress);
            }

            //telephone
            long newTelephone = Long.valueOf(request.queryParams("telephone"));
            patient.setTelephone(newTelephone);

            
            //email
            String newEmail = request.queryParams("email");
            if(newEmail !=null){
                patient.setEmail(newEmail);
            }

            Ebean.update(patient);
            response.status(204); // 204 No Content
            return "";
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
        }
    }

}
