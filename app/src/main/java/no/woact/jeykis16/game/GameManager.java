package no.woact.jeykis16.game;


import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import no.woact.jeykis16.R;

import static no.woact.jeykis16.ui.fragment.HighScoreFragment.TAG;

public class GameManager {
    private boolean multiplayer;
    private String playerOneName;
    private String playerTwoName;
    private ImageButton[][] gridButtons;
    private PlayerType[][] gameState;
    private PlayerType currentPlayer;
    private ArtificialPlayer ai;
    private String winner;
    private boolean gameFinished;
    private boolean draw;
    private int currentRound = 0;

    public GameManager(boolean multiplayer, String playerOne, String playerTwo) {
        this.multiplayer = multiplayer;
        this.playerOneName = playerOne;
        this.playerTwoName = playerTwo;
        this.currentPlayer = PlayerType.CROSS;
        this.winner = null;

        if (!isMultiplayer()) {
            ai = new ArtificialPlayer();
        }

        gameState = new PlayerType[3][3];
        resetGameState();

    }

    public void newRound(ImageButton[][] gridButtons) {
        if (gameFinished) {
            incrementRound();
        }
        resetGameState();
        this.currentPlayer = PlayerType.CROSS;
        this.winner = null;
        this.gameFinished = false;
        this.draw = false;
        setGridButtons(gridButtons);

        if (!isMultiplayer()) {
            ai.setGridButtons(gridButtons);
            if (getCurrentRound() == 1) {
                makeMove(ai.getMove(gameState, currentPlayer), true);
            }
        }
    }

    public void makeMove(ImageButton gridBtn, boolean botMove) {
        if (!isGameFinished()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gridButtons[i][j].equals(gridBtn)) {
                        if (gameState[i][j].equals(PlayerType.NONE)) {
                            gridBtn.setImageResource(getPlayerDrawable(currentPlayer));
                            gameState[i][j] = currentPlayer;
                            gridBtn.setEnabled(false);
                            if (!checkGameFinished()) {
                                Log.i(TAG, "makeMove: Game not finished for " + currentPlayer);
                                swapCurrentPlayer();
                                if (!isMultiplayer() && !botMove) {
                                    makeMove(ai.getMove(gameState, currentPlayer), true);
                                }
                            } else {
                                gameFinished = true;
                                if (!draw) {
                                    if ((currentPlayer == PlayerType.CROSS && getCurrentRound() == 0) ||
                                            (currentPlayer == PlayerType.CIRCLE && getCurrentRound() == 1)) {
                                        winner = playerOneName;
                                    } else if ((currentPlayer == PlayerType.CROSS && getCurrentRound() == 1) ||
                                            (currentPlayer == PlayerType.CIRCLE && getCurrentRound() == 0)) {
                                        winner = playerTwoName;
                                    }
                                }
                            }

                        } else {
                            Toast.makeText(gridBtn.getContext(), "Invalid move!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    private void resetGameState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState[i][j] = PlayerType.NONE;
            }
        }
    }
    private int getPlayerDrawable(PlayerType currentPlayer) {
        if (currentPlayer.equals(PlayerType.CROSS)) {
            return R.drawable.cross;
        } else {
            return R.drawable.circle;
        }
    }


    private void swapCurrentPlayer() {
        if (currentPlayer.equals(PlayerType.CROSS)) {
            currentPlayer = PlayerType.CIRCLE;
        } else {
            currentPlayer = PlayerType.CROSS;
        }
    }


    private boolean checkGameFinished() {
        if (GameHelper.moveEndsGame(gameState, currentPlayer)) return true;
        if (GameHelper.checkDraw(gameState)) {
            draw = true;
            return true;
        }
        return false;
    }


    public void setGridButtons(ImageButton[][] gridButtons) {
        this.gridButtons = gridButtons;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public String getWinner() {
        return winner;
    }

    public int getCurrentRound() {
        return currentRound % 2;
    }

    public void incrementRound() {
        currentRound++;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}
