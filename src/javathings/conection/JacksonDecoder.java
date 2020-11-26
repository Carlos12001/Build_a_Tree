package javathings.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JacksonDecoder {
    public static void main(String[] args) throws IOException {
        // NO borrar porque ocupamos obtener el path de otra manera
        File data = Paths.get("/home/mauro/Documents/GitKrakenClones/Build_a_Tree/src/data/data.json").toFile();

        UpdateInfo info = new JacksonDecoder().DecodeFile(data);

        System.out.println(info.getTime());

    }

    public UpdateInfo Decode(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UpdateInfo newInfo = mapper.readValue(json, UpdateInfo.class);
        return newInfo;
    }

    public UpdateInfo DecodeFile(File json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UpdateInfo newInfo = mapper.readValue(json, UpdateInfo.class);
        return newInfo;
    }

}
