package shauryas.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseConfig;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class TicTacToe extends Activity {

    private int[] symid = new int[9];
    private Button[] sym = new Button[9];
    private String[][] symTextCheck = new String[3][3];
    private boolean isMulti;
    private boolean win;
    private boolean lose;
    private boolean tie;
    private TextView dispWinOrLose;
    private String userSymbol;
    private String enemySymbol;
    private int soloDiff;
    private Boolean[] enabled = new Boolean[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        isMulti = Params.isMulti;

        win = false;
        lose = false;
        tie = false;

        dispWinOrLose = (TextView) findViewById(R.id.dispWinOrLose);

        userSymbol = Params.userSymbol;
        enemySymbol = Params.enemySymbol;

        soloDiff = Params.soloDifficulty;

        setButtonIds();
        setButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tic_tac_toe, menu);
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

    public void placeSymbol(View view){

        for(int i = 0; i<sym.length; i++){

            if(view.getId()==symid[i]){

                sym[i].setText(userSymbol);
                sym[i].setEnabled(false);
                break;

            }

        }

        checkWin();

        if(win==false){

            if(isMulti){

                //send data using parse

            }
            else{

                for(int i = 0; i<sym.length; i++) {

                    if(sym[i].isEnabled()==true){
                        enabled[i] = true;
                    }
                    else{
                        enabled[i] = false;
                    }
                    sym[i].setEnabled(false);

                }

                computerTurn();

            }

        }

    }

    public void checkWin(){

        setArrayForCheck();

        for(int i = 0; i<symTextCheck[0].length; i++){

            String[] symChecker = new String[symTextCheck.length];

            for(int j = 0; j<symTextCheck.length; j++){

                symChecker[j] = symTextCheck[j][i];

            }

            if(symChecker[0].equalsIgnoreCase(symChecker[1]) && symChecker[0].equalsIgnoreCase(userSymbol) && symChecker[0].equals(symChecker[2]) && symChecker[0] != "" && symChecker[1] != "" && symChecker[2] != ""){

                win();
                break;

            }

            else continue;

        }

        for(int i = 0; i<symTextCheck.length; i++){

            String[] symChecker = new String[symTextCheck.length];

            for(int j = 0; j<symTextCheck[i].length; j++){

                symChecker[j] = symTextCheck[i][j];

            }

            if(symChecker[0].equalsIgnoreCase(symChecker[1]) && symChecker[0].equalsIgnoreCase(userSymbol) && symChecker[0].equals(symChecker[2]) && symChecker[0] != "" && symChecker[1] != "" && symChecker[2] != ""){

                win();
                break;

            }

            else continue;

        }

        if(symTextCheck[0][0].equalsIgnoreCase(symTextCheck[1][1]) && symTextCheck[0][0].equalsIgnoreCase(userSymbol) && symTextCheck[0][0].equalsIgnoreCase(symTextCheck[2][2]) && symTextCheck[0][0] != "" && symTextCheck[1][1] != "" && symTextCheck[2][2] != ""){

            win();

        }

        if(symTextCheck[2][0].equalsIgnoreCase(symTextCheck[1][1]) && symTextCheck[2][0].equalsIgnoreCase(userSymbol) && symTextCheck[2][0].equalsIgnoreCase(symTextCheck[0][2]) && symTextCheck[2][0] != "" && symTextCheck[1][1] != "" && symTextCheck[0][2] != ""){

            win();

        }

        //check tie



    }

    public void checklose(){

        setArrayForCheck();

        for(int i = 0; i<symTextCheck[0].length; i++){

            String[] symChecker = new String[symTextCheck.length];

            for(int j = 0; j<symTextCheck.length; j++){

                symChecker[j] = symTextCheck[j][i];

            }

            if(symChecker[0].equalsIgnoreCase(symChecker[1]) && symChecker[0].equalsIgnoreCase(enemySymbol) && symChecker[0].equals(symChecker[2]) && symChecker[0] != "" && symChecker[1] != "" && symChecker[2] != ""){

                lose();
                break;

            }

            else continue;

        }

        for(int i = 0; i<symTextCheck.length; i++){

            String[] symChecker = new String[symTextCheck.length];

            for(int j = 0; j<symTextCheck[i].length; j++){

                symChecker[j] = symTextCheck[i][j];

            }

            if(symChecker[0].equalsIgnoreCase(symChecker[1]) && symChecker[0].equalsIgnoreCase(enemySymbol) && symChecker[0].equals(symChecker[2]) && symChecker[0] != "" && symChecker[1] != "" && symChecker[2] != ""){

                lose();
                break;

            }

            else continue;

        }

        if(symTextCheck[0][0].equalsIgnoreCase(symTextCheck[1][1]) && symTextCheck[0][0].equalsIgnoreCase(enemySymbol) && symTextCheck[0][0].equalsIgnoreCase(symTextCheck[2][2]) && symTextCheck[0][0] != "" && symTextCheck[1][1] != "" && symTextCheck[2][2] != ""){

            lose();

        }

        if(symTextCheck[2][0].equalsIgnoreCase(symTextCheck[1][1]) && symTextCheck[2][0].equalsIgnoreCase(enemySymbol) && symTextCheck[2][0].equalsIgnoreCase(symTextCheck[0][2]) && symTextCheck[2][0] != "" && symTextCheck[1][1] != "" && symTextCheck[0][2] != ""){

            lose();

        }

        //check tie



    }

    public void setButtons(){

        for(int i = 0; i<sym.length; i++){

            sym[i] = (Button) findViewById(symid[i]);

        }

    }

    public void setButtonIds(){

        symid[0] = R.id.button;
        symid[1] = R.id.button2;
        symid[2] = R.id.button3;
        symid[3] = R.id.button4;
        symid[4] = R.id.button5;
        symid[5] = R.id.button6;
        symid[6] = R.id.button7;
        symid[7] = R.id.button8;
        symid[8] = R.id.button9;

    }

    public void setArrayForCheck(){

        int symTextNum = 0;

        for(int i = 0; i<symTextCheck[0].length; i++){

            for(int j = 0; j<symTextCheck.length; j++){

                symTextCheck[j][i] = sym[symTextNum].getText().toString();
                symTextNum++;

            }

        }

    }

    public void win(){

        for(int i = 0; i<sym.length; i++) {

            sym[i].setEnabled(false);

        }

        win = true;
        lose = false;
        tie = false;

        Params.points+=2;
        Params.gamesWon++;

        dispWinOrLose.setText("You Win");

    }

    public void lose(){

        for(int i = 0; i<sym.length; i++) {

            sym[i].setEnabled(false);

        }

        win = false;
        lose = true;
        tie = false;

        Params.points--;
        Params.gamesLost++;

        dispWinOrLose.setText("You Lose");

    }

    public void tie(){

        for(int i = 0; i<sym.length; i++) {

            sym[i].setEnabled(false);

        }

        win = false;
        lose = false;
        tie = true;

        Params.points++;
        Params.gamesTied++;

        dispWinOrLose.setText("You Tied");

    }

    public void computerTurn(){

        if(soloDiff==1){

            performEasy();
            checklose();

        }

        if(soloDiff==2){

            performAdvanced();
            checklose();

        }

    }

    public void performEasy(){

        int chooseButton = (int)(Math.random()*sym.length);

        if(enabled[chooseButton]==true){

            sym[chooseButton].setText(enemySymbol);
            enabled[chooseButton] = false;

            for(int i = 0; i<sym.length; i++) {

                sym[i].setEnabled(enabled[i]);

            }

        }

        else{

            performEasy();

        }

    }

    public void performAdvanced(){



    }

    public void resetGame(View view){

        recreate();

    }

}


