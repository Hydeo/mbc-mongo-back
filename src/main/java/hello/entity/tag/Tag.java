package hello.entity.tag;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Tag {
    @Id
    public ObjectId _id;
    public String tagName;
    public HashMap<String, TagTrad> localization;
    public ArrayList<ObjectId> gameIds;
}
