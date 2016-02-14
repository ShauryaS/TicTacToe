package shauryas.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;

public class Profile extends AppCompatActivity {

    private TextView userNametv;
    private TextView pointstv;
    private TextView gamesWontv;
    private TextView gamesLosttv;
    private TextView gamesTiedtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userNametv = (TextView) findViewById(R.id.textView);
        pointstv = (TextView) findViewById(R.id.textView2);
        gamesWontv = (TextView) findViewById(R.id.textView3);
        gamesLosttv = (TextView) findViewById(R.id.textView4);
        gamesTiedtv = (TextView) findViewById(R.id.textView5);

        setUpSettingsContents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    public void setUpSettingsContents(){

        userNametv.setText("Username: " + ParseUser.getCurrentUser().getUsername());
        pointstv.setText("Points: " + Params.points);
        gamesWontv.setText("Games Won: " + Params.gamesWon);
        gamesLosttv.setText("Games Lost: " + Params.gamesLost);
        gamesTiedtv.setText("Games Tied: " + Params.gamesTied);

    }

}
