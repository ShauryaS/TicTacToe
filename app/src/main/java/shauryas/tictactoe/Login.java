package shauryas.tictactoe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    private EditText usernameview;
    private EditText passwordview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameview = (EditText) findViewById(R.id.usernamegetterlogin);
        passwordview = (EditText) findViewById(R.id.passwordgetterlogin);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void login(View view){

        String tagMsg = "Please ";
        boolean validationError = false;

        if(isEmpty(usernameview)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg += "enterusername";
        }

        if(isEmpty(passwordview)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg += "enter password";
        }

        tagMsg+=".";

        if(validationError){

            Toast.makeText(Login.this, tagMsg, Toast.LENGTH_LONG);

            return;

        }

        Params.username = usernameview.getText().toString();
        logUserIn();

    }

    public void signUp(View view){

        startActivity(new Intent(getApplicationContext(), SignUpScreen.class));

    }

    public boolean isEmpty(EditText et){

        if(et.getText().toString().trim().length()>0){
            return false;
        }
        else return true;

    }

    public void logUserIn(){

        final ProgressDialog dlg = new ProgressDialog(Login.this);
        dlg.setTitle("Please Wait");
        dlg.setMessage("Logging in. Please wait.");
        dlg.show();

        ParseUser.logInInBackground(usernameview.getText().toString(), passwordview.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                dlg.dismiss();
                if (e != null) {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Params.isLoggedOn=true;
                    Intent intent = new Intent(Login.this, Games.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }

}
