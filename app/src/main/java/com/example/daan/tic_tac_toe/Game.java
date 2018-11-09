package com.example.daan.tic_tac_toe;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public String getPlayerTurn(){
        if (playerOneTurn){
            return "Turn: Cross";
        } else {
            return "Turn: Circle";
        }
    }

    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK){
            if (playerOneTurn){
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            } else{
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        }
        return TileState.INVALID;
    }

    public GameState won(){
        TileState state;
        //checks is a row has the same values
        for (int i = 0; i<3; i++){
            state = board[i][0];
            if(state != TileState.BLANK && state == board[i][1] && state == board[i][2]){
                if(state == TileState.CROSS){
                    return GameState.PLAYER_ONE;
                } else{
                    return  GameState.PLAYER_TWO;
                }
            }
        }

        // checks if a column has the same values
        for (int i = 0; i<3; i++){
            state = board[0][i];
            if(state != TileState.BLANK && state == board[1][i] && state == board[2][i]){
                if(state == TileState.CROSS){
                    return GameState.PLAYER_ONE;
                } else{
                    return  GameState.PLAYER_TWO;
                }
            }
        }

        // checks diaganol if there are the same values
        state = board[1][1];
        if(state != TileState.BLANK && (state == board[0][0] && state == board[2][2] ||
            state == board[0][2] && state == board[2][0])){
            if(state == TileState.CROSS){
                return GameState.PLAYER_ONE;
            } else{
                return  GameState.PLAYER_TWO;
            }
        }

        // checks if there is an empty space
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (board[i][j] == TileState.BLANK){
                    return GameState.IN_PROGRESS;
                }
            }
        }

        return GameState.DRAW;
    }
}
