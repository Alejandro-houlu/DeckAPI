package sg.edu.nus.iss.CardAPI.Services;

import org.springframework.http.ResponseEntity;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.CardAPI.Models.Card;
import sg.edu.nus.iss.CardAPI.Models.Deck;

public interface DocInterface {
    

    public Deck createDeck();

    public String createUrl(String Url);

    public String createUrl(String url, String id, Integer drewCards);

    public JsonObject createJsonObj(ResponseEntity<String> resp);

    public Deck drawCards(String id, Integer drewCards);
    
}
