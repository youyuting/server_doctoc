package north.sample.spark.doctor;

import com.avaje.ebean.Ebean;
import north.sample.domain.Doctor;
import north.sample.spark.JsonTransformer;
import org.postgis.Point;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

/**
 * Created by yyou on 2016/12/8.
 */
public class GetDoctorsRoute extends JsonTransformer {

    public GetDoctorsRoute(String path) {
        super(path);
    }

    //Sample doctor
    @Override
    public Object handle(Request request, Response response) {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Pipi");
        doctor.setLastName("caca");
        doctor.setPassword("pwd");
        doctor.setAddress("12 rue du");
        doctor.setSex('T');
        doctor.setFac("cucu");
        doctor.setTelephone((long) 012020302);
        doctor.setEmail("ddd@dsdf.fr");
        doctor.setPhoto("ddd");
        /*try {
            doctor.setCurrentLocation(new Point("SRID=4674;POINT(2.8 1.7)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        Ebean.save(doctor);

        String neLat = request.queryParams("ne_lat");
        String neLng = request.queryParams("ne_lng");
        String swLat = request.queryParams("sw_lat");
        String swLng = request.queryParams("sw_lng");

        if (neLat != null && !neLat.isEmpty() &&
            neLng != null && !neLng.isEmpty() &&
            swLat != null && !swLat.isEmpty() &&
            swLng != null && !swLng.isEmpty() ) {
            return Ebean.find(Doctor.class).where().raw("currentLocation && ST_MakeEnvelope(?, ?, ?, ?)",new Double[]{Double.valueOf(neLat),Double.valueOf(swLng),Double.valueOf(swLat),Double.valueOf(neLng)}).findList();
        }

        return null;
    }

}
