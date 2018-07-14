package hello.entity;

public class User {


    public String uid;
    public String identifier;

    public User(){}

    public User(String UID, String identifier){
        this.uid = UID;
        this.identifier = identifier;
    }


    @Override
    public String toString(){
        return String.format("User[uid = %s,identifier=%s",uid,identifier);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
