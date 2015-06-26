package thisdomain.jasonrocks.firebase;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import thisdomain.jasonrocks.activities.IUpdateActivity;
import thisdomain.jasonrocks.activities.DetailedPostView;
import thisdomain.jasonrocks.model.Post;

/**
 * Created by admin on 6/25/2015.
 */

public class Source implements ISource {
    private Firebase root;
    private Firebase users;
    private Firebase feed;
    private final String url = "https://icon-demo.firebaseio.com";

    public Source(final IUpdateActivity updateActivity) {
        root = new Firebase(url);
        users = root.child("users");
        feed = root.child("feed").child("messages");

        feed.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Object snap = dataSnapshot.getValue();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void AddPost(Post post) {

    }

    @Override
    public Firebase getPost(String post_id) {
        return null;
    }

    @Override
    public Firebase getUser(String user_id) {
        return null;
    }
}
