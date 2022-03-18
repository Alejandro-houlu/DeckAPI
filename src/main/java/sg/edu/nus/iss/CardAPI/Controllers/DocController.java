package sg.edu.nus.iss.CardAPI.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.CardAPI.Models.Card;
import sg.edu.nus.iss.CardAPI.Models.Deck;
import sg.edu.nus.iss.CardAPI.Services.DocInterface;

@Controller
@RequestMapping(path="/doc", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocController {

    @Autowired
    DocInterface dService;

    private List<Card> cards = new ArrayList<>();

    @PostMapping("/deck")
    public String getDeck(Model model){

        this.cards.clear();

       Deck deck = dService.createDeck(); 

       model.addAttribute("deck", deck);

        return "deckView";
    }

    @PostMapping("/draw")
    public String drawCard(@RequestParam String id, @RequestParam String number, Model model){

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + id + " " + number);

        Deck deck = dService.drawCards(id, Integer.valueOf(number));

        for(Card c : deck.getCards()){
            cards.add(c);
        }

        deck.setCards(cards);        
        model.addAttribute("deck", deck);


        return "drawView";
    }
    
}
