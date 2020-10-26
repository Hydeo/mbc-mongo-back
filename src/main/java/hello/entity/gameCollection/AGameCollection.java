package hello.entity.gameCollection;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public abstract class AGameCollection {
    @Id
    public ObjectId _id;
    public String userId;
    public Boolean isPublic;
    //public ArrayList<ObjectId> gameIds;
    public HashMap<String,GameMask> gameMask;

}
