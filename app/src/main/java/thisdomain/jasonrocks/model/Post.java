package thisdomain.jasonrocks.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 6/25/2015
 */
public class Post implements Comparable<Post>, Serializable {

    private String author;
    private String message;
    private String Id;
    private String UID;
    private int likes;
    private Set<String> likeNames;
    private long timePosted;


    public Post(String author, String message, String UID)
    {
        this.author = author;
        this.message = message;
        this.Id = null;
        this.UID = UID;
        this.likeNames = new HashSet<>();
        likeNames.add(UID);
        this.likes = 0;
        timePosted = System.currentTimeMillis();
    }


    public Post()  //for firebase
    {
        this.likeNames = new HashSet<>();
    }

    public void likes(String UID) {
        if (!likeNames.contains(UID)) {
            likes++;
            likeNames.add(UID);
        }
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {this.Id = Id;}

    public int getlikes() {
        return likes;
    }

    public void setlikes(int likes) {
        this.likes = likes;
    }

    public Set<String> getlikeNames() {
        return likeNames;
    }

    public void setlikeNames(Set<String> likeNames) {
        this.likeNames = likeNames;
    }

    public long getTimePosted() {
        return timePosted;
    }

    @Override
    public int compareTo(Post another) {
        //later  == greater//
        return (int) (this.timePosted - another.timePosted);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Post))
        {
            return false;
        }
        Post p = (Post)o;
        return p.getId() == getId();
    }



}

