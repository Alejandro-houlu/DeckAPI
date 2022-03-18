package sg.edu.nus.iss.CardAPI.Services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.CardAPI.Models.Deck;

@Service
public class DocImplementation implements DocInterface {


    @Override
    public Deck createDeck() {
        String url = createUrl("https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>URL: " + url);

        RequestEntity<Void> req = RequestEntity
        .get(url)
        .build();

        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<String> resp = rTemplate.exchange(req,String.class);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>Response: " + resp.toString());

        JsonObject obj = createJsonObj(resp);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Json Deck: " + obj.toString());

        Deck deck = Deck.createModel(obj);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Deck: " + deck);



        return deck;
    }

    @Override
    public String createUrl(String url) {
        
        String newUrl = UriComponentsBuilder.fromUriString(url).toUriString();

        return newUrl;
    }

    @Override
    public String createUrl(String url, String id, Integer drewCards){

        String newUrl = UriComponentsBuilder.fromUriString(url)
                        .path(id).path("/draw/")
                        .queryParam("count", drewCards)
                        .toUriString();

        return newUrl;
    }

    @Override
    public JsonObject createJsonObj(ResponseEntity<String> resp) {
        
        JsonObject deck = null;

        try(InputStream file = new ByteArrayInputStream(resp.getBody().getBytes())){
            JsonReader jsonReader = Json.createReader(file);
            deck = jsonReader.readObject();
        } catch(IOException ex){
            //handle error
        }

        return deck;
    }

    @Override
    public Deck drawCards(String id, Integer drewCards){
        
        String url = createUrl("https://deckofcardsapi.com/api/deck/", id, drewCards);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>URL: " + url);

        RequestEntity<Void> req = RequestEntity
        .get(url)
        .build();

        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<String> resp = rTemplate.exchange(req, String.class);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Response: " + resp.toString());

        JsonObject jsonObj = createJsonObj(resp);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JsonObj: " + jsonObj);

        Deck deck = Deck.createModel(jsonObj);

        return deck;
    }


    
    
}
