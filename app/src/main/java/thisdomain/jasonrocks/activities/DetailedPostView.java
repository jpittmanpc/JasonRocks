package thisdomain.jasonrocks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.view.View;
import android.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Activity;
import thisdomain.jasonrocks.R;

import static android.view.View.*;
import thisdomain.jasonrocks.R;
import thisdomain.jasonrocks.firebase.Source;
import thisdomain.jasonrocks.model.Post;
import thisdomain.jasonrocks.model.User;


public class DetailedPostView extends Activity {

    Intent intent;

    TextView titleTextView;
    TextView contentTextView;
    private Bundle savedInstanceState;

    public DetailedPostView(Context context) {
        getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post_view);

        intent = getIntent();

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(intent.getStringExtra("title"));

        contentTextView = (TextView) findViewById(R.id.contentTextView);
        contentTextView.setText(intent.getStringExtra("content"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_post, menu);
        return true;
    }
}
/*
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

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public void setSavedInstanceState(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    public View getMenuInflater() {
        return View;

    }
}
*/