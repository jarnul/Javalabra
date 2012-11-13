/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

/**
 *
 * @author jzvaris
 */
public class gameAI {

    private gameLogic currentGame;

    public gameAI(gameLogic currentGame) {
        this.currentGame = currentGame;
    }

    public void updateGame() {
        int temp=0;
        while (lowestX() != this.currentGame.getBlockXco() && temp<6) {
            if (lowestX() < this.currentGame.getBlockXco()) {
                this.currentGame.movePiece(2);
            } else if (lowestX() > this.currentGame.getBlockXco()) {
                this.currentGame.movePiece(1);
            }
            ++temp;
        }
        this.currentGame.updateGame();
    }

    private int lowestX() {
        for (int j = this.currentGame.getGameStatus()[0].length - 1; j > -1; --j) {
            for (int i = 0; i < this.currentGame.getGameStatus().length; ++i) {
                if (this.currentGame.getGameStatus()[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
}