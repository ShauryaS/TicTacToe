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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpScreen extends AppCompatActivity {

    EditText usernamegetter;
    EditText emailgetter;
    EditText passwordgetter;
    EditText passwordconfirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        usernamegetter = (EditText) findViewById(R.id.usernamegetter);
        emailgetter = (EditText) findViewById(R.id.emailgetter);
        passwordgetter = (EditText) findViewById(R.id.passwordgetter);
        passwordconfirmer = (EditText) findViewById(R.id.passwordconfirmer);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up_screen, menu);
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

    public void signedUp(View view){

        String tagMsg = "Please ";
        boolean validationError = false;

        if(isEmpty(usernamegetter)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg += "enterusername";
        }

        if(isEmpty(emailgetter)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg += "enter email";
        }

        if(isEmpty(passwordgetter)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg += "enter password";
        }

        if(isEmpty(passwordconfirmer)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg += "confirm password";
        }

        if(!isMatching(passwordgetter, passwordconfirmer)){
            if(validationError){
                tagMsg+=", ";
            }
            validationError = true;
            tagMsg+="enter same password twice";
        }

        tagMsg+=".";

        if(validationError){

            Toast.makeText(SignUpScreen.this, tagMsg, Toast.LENGTH_LONG);

            return;

        }

        Params.username = usernamegetter.getText().toString();
        signUpUser();

    }

    public boolean isEmpty(EditText et){

        if(et.getText().toString().trim().length()>0){
            return false;
        }
        else return true;

    }

    public boolean isMatching(EditText et1, EditText et2){

        if(et1.getText().toString().equals(et2.getText().toString())){
            return true;
        }
        return false;

    }

    public void signUpUser(){

        final ProgressDialog dlg = new ProgressDialog(SignUpScreen.this);
        dlg.setTitle("Please Wait");
        dlg.setMessage("Signing up. Please wait.");
        dlg.show();


        ParseUser user = new ParseUser();
        user.setUsername(usernamegetter.getText().toString());
        user.setEmail(emailgetter.getText().toString());
        user.setPassword(passwordgetter.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dlg.dismiss();
                if(e!=null){
                    Toast.makeText(SignUpScreen.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Params.isLoggedOn=true;
                    Intent intent = new Intent(SignUpScreen.this, Games.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }

}
