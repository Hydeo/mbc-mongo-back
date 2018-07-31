package hello.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hello.utils.deserializer.ObjectIdDeserializer;

import java.io.IOException;

public class MyUtils {

    public MyUtils() {
    }

    public static JsonNode customObjectIdJsonMapper(Object o){

        String jsonString = new String();
        JsonNode jn = null ;

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new ObjectIdDeserializer());
        mapper.registerModule(module);
        try {
            jsonString = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            jn = mapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jn;
    }
}
