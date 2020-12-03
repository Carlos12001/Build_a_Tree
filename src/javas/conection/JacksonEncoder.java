package javas.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonEncoder {


    public String EncodeInfo(UpdateInfo message) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.INDENT_OUTPUT); //solo para printear bien
        String jsonString = mapper.writeValueAsString(message);

        //the field names of the objects become the JSON keys

        return jsonString;

    }

}
