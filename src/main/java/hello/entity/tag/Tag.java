package hello.entity.tag;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Tag {
    @Id
    ObjectId _id;

    String tagName;
    ArrayList<TagLang> listLocName;
    ArrayList<ObjectId> gameIds;

}
