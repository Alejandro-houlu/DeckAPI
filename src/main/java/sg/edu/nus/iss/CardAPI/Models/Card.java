package sg.edu.nus.iss.CardAPI.Models;

import jakarta.json.JsonObject;

public class Card {

    private String image;
    private String value;
    private String suit;
    private String code;

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getSuit() {
        return suit;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    } 

    public Card(String image, String value, String suit, String code){
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.code = code;
    }


    @Override
    public String toString() {
        return "Card [code=" + code + ", image=" + image + ", suit=" + suit + ", value=" + value + "]";
    }
    public static Card createModel(JsonObject obj){
        Card card = new Card(
            obj.getString("image"),
            obj.getString("value"),
            obj.getString("suit"),
            obj.getString("code")
        );

        return card;
    }

    
    
}
