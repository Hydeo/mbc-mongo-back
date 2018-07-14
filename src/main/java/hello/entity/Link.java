package hello.entity;

import org.springframework.data.annotation.Id;

public class Link {

    @Id
    public String id;

    public String owner;
    public String listName;
    public String url;
    public String author;
    public String description;
    public String image;
    public String publisher;
    public String title;

    public Link(){}

    public Link(String owner,String listName, String url, String author, String description, String image, String publisher, String title){
        this.owner = owner;
        this.listName = listName;
        this.url = url;
        this.author = author;
        this.description = description;
        this.image = image;
        this.publisher = publisher;
        this.title = title;
    }

    @Override
    public  String toString(){
        return String.format(
                "Link[id=%s,owner=%s,listName=%s, url=%s, author=%s, description=%s, image=%s, publisher=%s, title=%s]",
                id,owner,listName,url,author,description,image,publisher,title
        );
    }

    public String getOwner() {
        return owner;
    }

    public String getListName() {
        return listName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
