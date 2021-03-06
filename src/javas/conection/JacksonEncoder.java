package javas.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * This class is the one in charge of encoding a message
 */
public class JacksonEncoder {

    /**
     * THe constructor receives the Object UpdateInfo a changes its format to a jsonString
     * @param message Update info object
     * @return jsonString sent to the client
     * @throws JsonProcessingException una expecion
     */
    public String EncodeInfo(UpdateInfo message) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //solo para printear bien
        String jsonString = mapper.writeValueAsString(message);


        return jsonString;

    }

}
