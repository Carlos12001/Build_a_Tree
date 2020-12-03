package javas.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class JacksonDecoder {
    public String jsonString;

    public JacksonDecoder(String jsonString){
        this.jsonString = jsonString;
    }

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
