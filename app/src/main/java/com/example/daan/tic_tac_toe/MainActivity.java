package com.example.daan.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[] ids = {2131165216, 2131165217, 2131165218, 2131165219, 2131165220, 2131165221,
                            2131165222, 2131165223, 2131165224};
    private String[] buttons = {"b00", "b01", "b02", "b03", "b04", "b05", "b06", "b07", "b08"};
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            for(int i = 0; i<9; i++) {
                Button button = findViewById(ids[i]);
                button.setText(savedInstanceState.getString(buttons[i]));
            }
            TextView status = findViewById(R.id.status);
            status.setText(savedInstanceState.getString("status"));
            game = (Game) savedInstanceState.getSerializable("game");
        } else {
            game = new Game();
        }
    }

    public void tileClicked(View view) {
        GameState gameState = game.won();
        if (gameState == GameState.IN_PROGRESS) {
            int id = view.getId();
            int row = 0;
            int column = 0;
            switch (id) {
                case 2131165216:
                    row = 0;
                    column = 0;
                    break;
                case 2131165217:
                    row = 1;
                    column = 0;
                    break;
                case 2131165218:
                    row = 2;
                    column = 0;
                    break;
                case 2131165219:
                    row = 0;
                    column = 1;
                    break;
                case 2131165220:
                    row = 1;
                    column = 1;
                    break;
                case 2131165221:
                    row = 2;
                    column = 1;
                    break;
                case 2131165222:
                    row = 0;
                    column = 2;
                    break;
                case 2131165223:
                    row = 1;
                    column = 2;
                    break;
                case 2131165224:
                    row = 2;
                    column = 2;
                    break;
            }
            Button button = findViewById(id);
            TileState state = game.choose(row, column);
            switch (state) {
                case CROSS:
                    button.setText("Cross");
                    break;
                case CIRCLE:
                    button.setText("Circle");
                    break;
                case INVALID:
                    // do something different
                    break;
            }
            TextView status = findViewById(R.id.status);
            status.setText(game.getPlayerTurn());
        }
        gameState = game.won();
        TextView status = findViewById(R.id.status);
        if (gameState == GameState.PLAYER_ONE){
            status.setText("Cross won");
        } else if (gameState == GameState.PLAYER_TWO){
            status.setText("Circle won");
        } else if (gameState == GameState.DRAW) {
            status.setText("it is a draw!");
        }
    }

    public void resetClicked(View view) {
        game = new Game();
        TextView status = findViewById(R.id.status);
        status.setText("Turn: Cross");
        for(int i = 0; i<9; i++){
            Button button = findViewById(ids[i]);
            button.setText("");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i = 0; i<9; i++) {
            Button button = findViewById(ids[i]);
            outState.putString(buttons[i], button.getText().toString());
        }
        TextView status = findViewById(R.id.status);
        outState.putString("status", status.getText().toString());
        outState.putSerializable("game", game);
    }
}
