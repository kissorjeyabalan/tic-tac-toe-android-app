package no.woact.jeykis16.game;


import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import no.woact.jeykis16.R;

import static no.woact.jeykis16.ui.fragment.HighScoreFragment.TAG;

public class GameManager {
    private boolean isMultiplayer;
    private String playerOneName;
    private String playerTwoName;
    private ImageButton[][] gridButtons;
    private PlayerType[][] gameState;
    private PlayerType currentPlayer;
    private String winner;
    private boolean gameFinished;
    private boolean draw;
    private int currentRound = 0;

    public GameManager(boolean isMultiplayer, String playerOne, String playerTwo) {
        this.isMultiplayer = isMultiplayer;
        this.playerOneName = playerOne;
        this.playerTwoName = playerTwo;
        this.currentPlayer = PlayerType.CROSS;
        this.winner = null;

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
    }

    public void makeMove(ImageButton gridBtn) {
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
                                if (!isMultiplayer) {
                                    // TODO: AI make move
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
        if (checkRowWin()) return true;
        else if (checkColWin()) return true;
        else if (checkDiagonalWin()) return true;
        else if (checkDraw()) return true;
        return false;
    }

    private boolean checkRowWin() {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i][0].equals(currentPlayer) &&
                gameState[i][1].equals(currentPlayer) &&
                gameState[i][2].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColWin() {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[0][i].equals(currentPlayer) &&
                gameState[1][i].equals(currentPlayer) &&
                gameState[2][i].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        return
            ((gameState[0][0].equals(currentPlayer) &&
                    gameState[1][1].equals(currentPlayer) &&
                    gameState[2][2].equals(currentPlayer)) || (
                            gameState[2][0].equals(currentPlayer)  &&
                            gameState[1][1].equals(currentPlayer) &&
                            gameState[0][2].equals(currentPlayer)
            ));
    }

    private boolean checkDraw() {
        int occupiedSpaces = 0;
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                if (gameState[i][j] != PlayerType.NONE) {
                    occupiedSpaces++;
                }
            }
        }
        if (occupiedSpaces == (gridButtons.length * 3)) {
            draw = true;
            return true;
        }
        return false;
    }

    public void setGridButtons(ImageButton[][] gridButtons) {
        this.gridButtons = gridButtons;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
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
