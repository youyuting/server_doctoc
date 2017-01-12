package north.sample.spark;
import north.sample.spark.book.DeleteBookRoute;
import north.sample.spark.book.GetBooksRoute;
import north.sample.spark.book.GetBookRoute;
import north.sample.spark.book.PutBookRoute;
import north.sample.spark.book.PostBookRoute;
import north.sample.spark.doctor.GetDoctorRoute;
import north.sample.spark.doctor.GetDoctorsRoute;
import north.sample.spark.patient.*;
import spark.*;

public class Bootstrap {

    public static void main(String[] args) {
        Spark.setPort(8085);

        Spark.before(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                String url = request.url();

                if (!url.contains("/patients/login") &&!url.contains("/patients/signIn") && request.session().attribute("user") == null) {
                    halt(401, "Unauthorized !!!");
                }
            }
        });

        Spark.get(new GetBooksRoute("/books"));
        Spark.get(new GetBookRoute("/books/:id"));
        Spark.post(new PostBookRoute("/books"));
        Spark.put(new PutBookRoute("/books/:id"));
        Spark.delete(new DeleteBookRoute("/books/:id"));

        Spark.post(new PostPatientLoginRoute("/patients/login"));
        Spark.get(new GetPatientRoute("/patients/:id"));
        Spark.post(new PostPatientRoute("/patients/signIn"));
        Spark.put(new PutPatientRoute("/patients/:id"));
        Spark.delete(new DeletePatientRoute("/patients/:id"));

        Spark.get(new GetDoctorRoute("/doctors/:id"));
        Spark.get(new GetDoctorsRoute("/doctors"));

        Spark.get(new Route("/count") {
            @Override
            public Object handle(Request request, Response response) {
                if(request.session().attribute("counter") != null) {
                    request.session().attribute("counter", ((int)request.session().attribute("counter"))+1);
                } else {
                    request.session().attribute("counter", 1);
                }
                return request.session().attribute("counter");
            }
        });
    }
}
