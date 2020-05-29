package hello.entity.gameCollection;

import hello.entity.game.Game;
import org.bson.types.ObjectId;

public class GameMask{

    public String price;
    public String comment;
    public Float rating;
    public Game override;

    public GameMask() {
    }

    public GameMask( String price, String comment, Float rating, Game override) {
        this.price = price;
        this.comment = comment;
        this.rating = rating;
        this.override = override;
    }

    public GameMask( String price, String comment, Float rating) {
        this.price = price;
        this.comment = comment;
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
