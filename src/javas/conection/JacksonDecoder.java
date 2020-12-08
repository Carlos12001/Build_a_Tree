package javas.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 *This class is the one used to decode a mesage, changing its format from an incoming json string
 * to an UpdateInfo Object
 */
public class JacksonDecoder {
    public String jsonString;

    public JacksonDecoder(String jsonString){
        this.jsonString = jsonString;
    }

    /**
     * This method uses the info of the json string setted in the constructor of the class, and the
     * ObjectMapper Class to return an UpdateInfo Object
     * @return
     * @throws JsonProcessingException
     */
    public UpdateInfo Decode() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UpdateInfo newInfo = mapper.readValue(this.jsonString, UpdateInfo.class);
        return newInfo;
    }

    public UpdateInfo DecodeFile(File json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UpdateInfo newInfo = mapper.readValue(json, UpdateInfo.class);
        return newInfo;
    }


}
