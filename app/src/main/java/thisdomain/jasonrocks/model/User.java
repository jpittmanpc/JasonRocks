package thisdomain.jasonrocks.model;

import com.firebase.client.Firebase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by admin on 6/25/2015.
 */
public class User implements Serializable {


    private ArrayList<Post> posts = new ArrayList<>();

    public User(int me) {

    }

    public int getId() {
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
