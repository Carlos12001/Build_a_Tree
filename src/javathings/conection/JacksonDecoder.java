package javathings.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class JacksonDecoder {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        UpdateInfo completeDeck = mapper.readValue(Paths.get("/home/mauro/Documents/GitKrakenClones/Build_a_Tree/src/data/data.json").toFile(), UpdateInfo.class);

        String[] cards = completeDeck.getPlayersName();

        System.out.println(cards[0]);

        /*String json =
                "{"
                        + "'playersName': ['Nacho', 'Carlos', 'Mauro', '' ],"
                        + "'playersGameOver': [true, true, true, true],"
                        + "'treeB': 'treeSplay@23',"
                        + "'treeBST': 'treeBST@16',"
                        + "'treeAVL': 'treeAVL@16',"
                        + "'treeSplay': 'treeSplay@1',"
                        + "'time': 5000,"
                        + "'tokenSend': 'treeAVL@16',"
                        + "'challenge': ['treeB', '', 'treeAVL', '']"
                        + "}";

        UpdateInfo info = new JacksonDecoder().Decode(json);

        System.out.println(info);*/
    }

    public UpdateInfo Decode(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UpdateInfo newInfo = mapper.readValue(json, UpdateInfo.class);
        return newInfo;
    }

}
