package shauryas.tictactoe;

import android.app.Application;

import com.parse.Parse;

public class InitializeParse extends Application{

    public void onCreate(){
        Parse.initialize(this, "odtUkADjdx4OI3B7XsDcaoSemppXcG98N4I9oqYy", "8kjXJk2PzR1ynJLNSTBOPLyUwAtP9lkibmuwcAnZ");
    }

}
