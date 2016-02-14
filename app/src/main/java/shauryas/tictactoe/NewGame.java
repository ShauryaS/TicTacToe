package shauryas.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseQuery;
import com.parse.ParseUser;

public class NewGame extends AppCompatActivity {//same as new game maker

    private EditText searchusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        searchusers = (EditText) findViewById(R.id.enterusersearch);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
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

    public void playUser(View view){

        determineSymbol();
        Params.isMulti = true;
        startActivity(new Intent(getApplicationContext(), TicTacToe.class));

    }

    public void playSolo(View view){

        determineSymbol();

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setMessage("Select a Difficulty");
        ad.setNeutralButton("Basic", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Params.soloDifficulty = 1;
                Params.isMulti = false;
                startActivity(new Intent(getApplicationContext(), TicTacToe.class));
            }
        });
        ad.setNegativeButton("Advanced", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Params.soloDifficulty = 2;
                Params.isMulti = false;
                startActivity(new Intent(getApplicationContext(), TicTacToe.class));
            }
        });
        ad.show();

    }

    public void determineSymbol(){

        int x = (int)(Math.random()*1000+1);

        if(x%2==0){
            Params.userSymbol = "X";
            Params.enemySymbol = "O";
        }
        else{
            Params.userSymbol = "O";
            Params.enemySymbol = "X";
        }
    }

}
