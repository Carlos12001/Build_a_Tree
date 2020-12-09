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
    /**
     * Saves the jsonString that is about to be decoded
     */
    public String jsonString;

    /**
     * Constructor
     * @param jsonString assigns the value for the jsonString attribute
     */
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

    /**
     * Decodes a file in json format
     * @param json file about to be decoded
     * @return Update info object
     * @throws IOException
     */
    public UpdateInfo DecodeFile(File json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UpdateInfo newInfo = mapper.readValue(json, UpdateInfo.class);
        return newInfo;
    }


}
