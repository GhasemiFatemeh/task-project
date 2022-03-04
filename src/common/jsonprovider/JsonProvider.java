package common.jsonprovider;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonProvider {
    public static String toJson(List list) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.writeValueAsString(list);
    }
}
