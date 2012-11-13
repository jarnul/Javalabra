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
    
    public gameAI(){

    }
    
    public void updateGame(gameLogic currentGame){
        if (lowestX(currentGame) < currentGame.getBlockXco()) {
            currentGame.movePiece(2);
        }
        else if (lowestX(currentGame) > currentGame.getBlockXco()) {
            currentGame.movePiece(1);
        }
        currentGame.updateGame();
    }
    
    private int lowestX(gameLogic currentGame) {
        for (int i=0;i<currentGame.getGameStatus().length;++i){
            for (int j=currentGame.getGameStatus()[i].length-1;j>-1;--j){
                if (currentGame.getGameStatus()[i][j]==0){
                    return i;
                }
            }
        }
        return -1;
    }
}