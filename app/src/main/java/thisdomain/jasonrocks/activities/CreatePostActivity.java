package thisdomain.jasonrocks.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thisdomain.jasonrocks.R;
import thisdomain.jasonrocks.firebase.Source;
import thisdomain.jasonrocks.firebase.ISource;
import thisdomain.jasonrocks.model.Post;

public class CreatePostActivity extends Activity {

    Intent intent;

    private EditText titleEditText;
    private EditText postEditText;
    private Button submitButton, cancelButton;
    private ISource mSource;

    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        intent = getIntent();

        mSource = new Source(null);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        postEditText = (EditText) findViewById(R.id.postEditText);
        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        title = "";
        content = "";

        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        title = titleEditText.getText().toString();
                        content = postEditText.getText().toString();

                        Post post = new Post(title, content, intent.getStringExtra("user_id"));
                        mSource.AddPost(post);
                        //post now has id set.
                        Toast.makeText(CreatePostActivity.this, "Submit Clicked!", Toast.LENGTH_SHORT).show();

                        ((Activity) v.getContext()).finish();

                    }
                }
        );

        cancelButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        //TODO: Go back to home page.
                        Toast.makeText(CreatePostActivity.this, "Cancel Clicked!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
