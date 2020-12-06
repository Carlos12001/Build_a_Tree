package javas.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonEncoder {


    public String EncodeInfo(UpdateInfo message) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //solo para printear bien
        String jsonString = mapper.writeValueAsString(message);


        return jsonString;

    }

}
