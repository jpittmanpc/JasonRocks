package thisdomain.jasonrocks;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends Activity {

    public static final String url = "https://icon-demo.firebaseio.com";
    public Firebase fbref;
    //Variables for the input fields & the button
    EditText email;
    EditText password;
    Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //---------Setting the Layout to be fragment_main-----------
        setContentView(R.layout.fragment_main);
        //Setting up Firebase to be "Android Context"
        Firebase.setAndroidContext(this);
        final Firebase fbref = new Firebase(url);
        fbref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    System.out.println("user is logged in as " + authData.getUid());
                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                    Intent bypassLogin = new Intent( getBaseContext() , Activity2.class );
                    startActivity( bypassLogin );
                } else {
                    System.out.println("user logged out.");
                }
            }
        });

        //Variables for the input fields & the button
        final EditText emailField = (EditText) findViewById(R.id.email);
        final EditText passField = (EditText) findViewById(R.id.password);
        final Button button=(Button) findViewById(R.id.loginbutton);
        button.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                //This is the text that they input
                String emailText = emailField.getText().toString();
                String passwordText = passField.getText().toString();
                System.out.println("User: " + emailText + "pass: " + passwordText);
                fbref.authWithPassword(emailText, passwordText, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID:" + authData.getUid() + " authenticated with " + authData.getProvider());
                        Intent i = new Intent(getBaseContext(), Activity2.class);
                        startActivity(i);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("Error:" + firebaseError);
                    }
                });
            }
        });




    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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











