package north.sample.spark;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import spark.ResponseTransformerRoute;

public abstract class JsonTransformer extends ResponseTransformerRoute {

    protected static final ObjectMapper mapper = new ObjectMapper();

    protected JsonTransformer(String path) {
        super(path);
    }

    protected JsonTransformer(String path, String acceptType) {
        super(path, acceptType);
    }

    @Override
    public String render(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (IOException ex) {
            return "Serialization error";
        }
    }

    protected static Map<String, String> createErrorResponse(String message) {
        HashMap<String, String> response = new HashMap<>();
        response.put("reason", message);
        return response;
    }
}
