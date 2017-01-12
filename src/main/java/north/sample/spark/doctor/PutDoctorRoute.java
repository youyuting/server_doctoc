package north.sample.spark.doctor;

import com.avaje.ebean.Ebean;
import north.sample.domain.Doctor;
import north.sample.spark.JsonTransformer;
import spark.Request;
import spark.Response;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yyou on 2016/12/6.
 */
public class PutDoctorRoute extends JsonTransformer {

    public PutDoctorRoute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        long doctorId = Long.parseLong(request.params(":id"));
        Doctor doctor = Ebean.find(Doctor.class, doctorId);
        if (doctor != null) {
            //password
            String newPassword = request.queryParams("password");
            if (newPassword != null) {
                doctor.setPassword(newPassword);
            }
            
            //lastName
            String newLastName = request.queryParams("lastName");
            if (newLastName != null) {
                doctor.setLastName(newLastName);
            }

            //firstName
            String newFirstName = request.queryParams("firstName");
            if (newFirstName != null) {
                doctor.setLastName(newFirstName);
            }

            //address
            String newAddress = request.queryParams("address");
            if (newAddress != null) {
                doctor.setAddress(newAddress);
            }

            //fac
            String newFac = request.queryParams("fac");
            if (newFac != null) {
                doctor.setFac(newFac);
            }

            //telephone
            long newTelephone = Long.valueOf(request.queryParams("telephone"));
            doctor.setTelephone(newTelephone);

            //email
            String newEmail = request.queryParams("email");
            if(newEmail !=null){
                doctor.setEmail(newEmail);
            }

            //pic
            // String newPhoto =request.queryParams("photo");
            // if(newPhoto !=null){
            //    doctor.setPhoto(newPhoto);
            //}

            try {
                Part uploaded_file = request.raw().getPart("uploaded_file");
                if (uploaded_file != null) {
                    try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
                        // Use the input stream to create a file
                        byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(is);
                        doctor.setPicture(byteArray);
                    }
                }
            } catch (IOException | ServletException e) {
              response.status(500); // Server-side error
              return "error";
            }
            Ebean.update(doctor);
            response.status(204); // 204 No Content
            return "";
        } else {
            response.status(404); // 404 Not found
            return createErrorResponse("Not found");
            }
    }
}
