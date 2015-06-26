package thisdomain.jasonrocks.firebase;

import com.firebase.client.Firebase;
import thisdomain.jasonrocks.model.Post;

public interface ISource
{
    void AddPost(Post post);

    Firebase getPost(String post_id);

    Firebase getUser(String user_id);
}
