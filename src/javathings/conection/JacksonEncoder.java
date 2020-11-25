package javathings.conection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Paths;

public class JacksonEncoder {
    public static void main(String[] args) throws JsonProcessingException {
        /*String cardA = encodeCard (new PlayingCard("H","Rumble",40));
        String cardB = encodeCard (new PlayingCard("A","120",30));
        String cardC = encodeCard (new PlayingCard("A","100",42));
        String cardD = encodeCard (new PlayingCard("S","TicTac",15));
        LinkedLists cardList = new LinkedLists();
        cardList.insert(cardA);
        cardList.insert(cardB);
        cardList.insert(cardC);
        cardList.insert(cardD);

        //encodeHand(cardList);

        encodeMessage(new Message(".newPlayer","22554"));
        encodeMessage(new Message("action",null, true));
        encodeMessage(new Message("action",true,16,null,100,100));*/

    }

    public String encodeMessage(UpdateInfo message) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //solo para printear bien
        String jsonString = mapper.writeValueAsString(message);

        //the field names of the objects become the JSON keys
        System.out.println(jsonString);
        return jsonString;

    }

}
