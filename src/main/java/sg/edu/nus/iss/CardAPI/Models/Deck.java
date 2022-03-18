package sg.edu.nus.iss.CardAPI.Models;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Deck {

    private String id;
    private Boolean shuffled;
    private Integer remainingCards;
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Boolean getShuffled() {
        return shuffled;
    }
    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }
    public Integer getRemainingCards() {
        return remainingCards;
    }
    public void setRemainingCards(Integer remainingCards) {
        this.remainingCards = remainingCards;
    }

    public Deck(String id, Boolean shuffled, Integer remainingCards){
        this.id = id;
        this.shuffled = shuffled;
        this.remainingCards = remainingCards;
    }

    public Deck(String id, Boolean shuffled, Integer remainingCards, List<Card> cards){

        this.id = id;
        this.shuffled = shuffled;
        this.remainingCards = remainingCards;
        this.cards = cards;
    }

    public Deck(){
    }

    @Override
    public String toString() {
        return "Deck [id=" + id + ", remainingCards=" + remainingCards + ", shuffled=" + shuffled + "]";
    }

    public static Deck createModel(JsonObject jsonObj){


        if(jsonObj.containsKey("cards")){

            JsonArray jsonCards = jsonObj.getJsonArray("cards");
            List<Card> cards =  
            jsonCards.stream().map(c -> (JsonObject) c).map(c->Card.createModel(c)).collect(Collectors.toList());


            Deck deck = new Deck(
                jsonObj.getString("deck_id"),
                true,
                jsonObj.getInt("remaining"),
                cards);

                return deck;
        }

        else{

        Deck deck = new Deck(
            jsonObj.getString("deck_id"),
            jsonObj.getBoolean("shuffled"),
            jsonObj.getInt("remaining")
            );

            return deck;
        }

    }

    

    
    
}
