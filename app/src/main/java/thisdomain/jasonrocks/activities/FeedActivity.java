package thisdomain.jasonrocks.activities;

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.provider.Settings;
    import android.app.Activity;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;
    import android.widget.TextView;

    import com.firebase.client.Firebase;


    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    import thisdomain.jasonrocks.R;
    import thisdomain.jasonrocks.firebase.ISource;
    import thisdomain.jasonrocks.firebase.Source;
import thisdomain.jasonrocks.model.Post;
    import thisdomain.jasonrocks.model.User;


public class FeedActivity extends Activity implements ISource {

    private String UID;

    private List<Post> postList;
    private User currentUser;

    private ISource Source;
    private ArrayAdapter<Post> postArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        UID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        //Instantiate source with activity as updateable
        Source  = new Source(this);

        //initialize post list to be updated later//
        postList = new ArrayList<>();

        final ListView postsListView = (ListView) findViewById(R.id.postsListView);

        //anon inner ArrayAdapter subclass to display likes//
        postArrayAdapter = new ArrayAdapter<Post>(this, R.layout.post_list_item,
                R.id.listItem_titleText, postList) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                //use default behavior to set main text with Post#toString()
                View postView = super.getView(position, convertView, parent);

                //set Title Text.
                final TextView titleTextView = (TextView) postView.findViewById(R.id.listItem_titleText);
                titleTextView.setText(getItem(position).getTitle());
                //set likes text//
                TextView likesTextView = (TextView) postView.findViewById(R.id.likesTextView);
                likesTextView.setText(Integer.toString(getItem(position).getlikes()));

                //set click listener to go to detailed post view
                postView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), DetailedPostView.class);
                        i.putExtra("UID", UID);
                        i.putExtra("title", titleTextView.getText());
                        i.putExtra("content", getItem(position).getmessage());
                        startActivity(i);
                    }
                });

                //set click listener to increment likes when TextView is clicked//
                likesTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Post postClicked = getItem(position);

                        //update data model//
                        postClicked.setlikeNames(UID);
                        Firebase postRef = thisdomain.jasonrocks.firebase.Source.getPost(postClicked.getId());
                        Map<String, Object> updates = new HashMap<>();

                        updates.put("liked", new ArrayList<Object>(postClicked.getlikeNames()));
                        updates.put("likes", postClicked.getlikes());
                        postRef.updateChildren(updates);

                        //update view//
                        ((TextView) v).setText(Integer.toString(getItem(position).getlikes()));

                        //TODO update remote database//
                        //Source.likes(postClicked.getId());

                    }
                });

                return postView;
            }
        };

        postsListView.setAdapter(postArrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_make_post) {

            //launch CreatePostActivity with user data//
            Intent intent = new Intent(this, CreatePostActivity.class);

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addPost(Post post) {
        if (postList == null) {
            postList = new ArrayList<>();
        }
        postList.add(0, post);
        postArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void updatePost(Post post) {
        if (postList == null) {
            postList = new ArrayList<>();
        }

        for (Post p : postList) {
            if (p.getId().equals(post.getId())) {
                p.setauthor(post.getauthor());
                p.setmessage(post.getmessage());
                p.setlikes(post.getlikes());
                p.setlikeNames(post.getlikeNames());
            }
        }


        postArrayAdapter.notifyDataSetChanged();
    }

    public void removePost(Post removed_post) {
        postList.remove(removed_post);
        postArrayAdapter.notifyDataSetChanged();
    }
}
