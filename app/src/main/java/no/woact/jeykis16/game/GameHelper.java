package no.woact.jeykis16.game;

public class GameHelper {
    public static final int GRID_LENGTH = 9;

    public static boolean checkRowWin(PlayerType[][] gameState, PlayerType player) {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i][0].equals(player) &&
                    gameState[i][1].equals(player) &&
                    gameState[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColWin(PlayerType[][] gameState, PlayerType player) {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[0][i].equals(player) &&
                    gameState[1][i].equals(player) &&
                    gameState[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonalWin(PlayerType[][] gameState, PlayerType player) {
        return
                ((gameState[0][0].equals(player) &&
                        gameState[1][1].equals(player) &&
                        gameState[2][2].equals(player)) || (
                        gameState[2][0].equals(player)  &&
                                gameState[1][1].equals(player) &&
                                gameState[0][2].equals(player)
                ));
    }

    public static boolean checkDraw(PlayerType[][] gameState) {
        int occupiedSpaces = 0;
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                if (gameState[i][j] != PlayerType.NONE) {
                    occupiedSpaces++;
                }
            }
        }
        return occupiedSpaces == GRID_LENGTH;
    }

    public static boolean moveEndsGame(PlayerType[][] gameState, PlayerType player) {
        return GameHelper.checkRowWin(gameState, player) ||
                GameHelper.checkColWin(gameState, player) ||
                GameHelper.checkDiagonalWin(gameState, player);
    }
}
