package no.woact.jeykis16.game;


import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ArtificialPlayer {
    private PlayerType aiType = PlayerType.NONE;
    private ImageButton[][] gridButtons;

    public ArtificialPlayer() {
    }


    public ImageButton getMove(PlayerType[][] gameState, PlayerType currentPlayer) {
        PlayerType[][] gameStateCopy = gameState.clone();
        aiType = currentPlayer;
        Move bestMove = minimax(gameStateCopy, currentPlayer);
        return gridButtons[bestMove.row][bestMove.col];
    }

    /* https://medium.freecodecamp.org/how-to-make-your-tic-tac-toe-game-unbeatable-by-using-the-minimax-algorithm-9d690bad4b37 */
    private Move minimax(PlayerType[][] gameState, PlayerType player) {
        Move end = new Move();
        end.score = -1;
        if (GameHelper.moveEndsGame(gameState, getOpponentType(aiType))) {
            end.score = -10;
        } else if (GameHelper.moveEndsGame(gameState, aiType)) {
            end.score = 10;
        } else if (GameHelper.checkDraw(gameState)) {
            end.score = 0;
        }

        if (end.score != -1) {
            return end;
        }

        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                if (gameState[i][j] == PlayerType.NONE) {
                    Move move = new Move();
                    move.row = i;
                    move.col = j;
                    gameState[i][j] = player;

                    if (player == aiType) {
                        Move result = minimax(gameState, getOpponentType(aiType));
                        move.score = result.score;
                    } else {
                        Move result = minimax(gameState, aiType);
                        move.score = result.score;
                    }

                    gameState[i][j] = PlayerType.NONE;
                    moves.add(move);
                }
            }
        }
        Move bestMove = null;
        if (player == aiType) {
            int bestScore = -10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score > bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = moves.get(i);
                }
            }
        } else {
            int bestScore = 10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score < bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = moves.get(i);
                }
            }
        }

        return bestMove;

    }

    private class Move {
        int col;
        int row;
        int score;
    }


    private PlayerType getOpponentType(PlayerType currentPlayer) {
        return currentPlayer == PlayerType.CROSS ? PlayerType.CIRCLE : PlayerType.CROSS;
    }

    public void setGridButtons(ImageButton[][] gridButtons) {
        this.gridButtons = gridButtons;
    }
}
