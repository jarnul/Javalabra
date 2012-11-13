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
    private int targetHeight;

    public gameAI(gameLogic currentGame) {
        this.currentGame = currentGame;
        this.targetHeight = 0;
    }
    
    /*
     * Method that moves block to desired spot
     */

    public void updateGame() {
        int temp=0;
        int goalX=tryAllSpots(new gameBlock(this.currentGame.getCurrentBlockType()));
        while (goalX != this.currentGame.getBlockXco() && temp<6) {
            if (goalX < this.currentGame.getBlockXco()) {
                this.currentGame.movePiece(2);
            } else if (goalX > this.currentGame.getBlockXco()) {
                this.currentGame.movePiece(1);
            }
            ++temp;
        }
        this.currentGame.updateGame();
    }
    
    /*
     * Method to try block in all possible spots
     */
    
    private int tryAllSpots(gameBlock testBlock){
        int upperlimit=highestX(this.currentGame.getGameStatus());
        int lowest=this.currentGame.getGameStatus()[0].length;
        int xCo=0;
        for (int i=0;i<this.currentGame.getGameStatus().length;++i){
            for (int j=this.currentGame.getGameStatus()[0].length-1;j>upperlimit-1;--j){
                if (!checkForCollisionAi(testBlock.getBlockStructure(), this.currentGame.getGameStatus(), i, j)) {
                    int highPoint=highestX(fillInStatus(testBlock, copyTable(this.currentGame.getGameStatus()), i, j));
                    if (lowest>highPoint){
                        lowest=highPoint;
                        this.targetHeight=highPoint;
                        xCo=i;
                    }
                }
            }
        }
        return xCo;
    }
    
    /*
     * Method to find the lowest X-coordinate which is empty
     */

    private int lowestX(int[][] gameTable) {
        for (int j = gameTable[0].length - 1; j > -1; --j) {
            for (int i = 0; i < gameTable.length; ++i) {
                if (gameTable[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /*
     * Method to find the highest row that is not empty
     */
    
    private int highestX(int[][] gameTable) {
        for (int j = gameTable[0].length - 1; j > -1; --j) {
            boolean empty=true;
            for (int i = 0; i < gameTable.length; ++i) {
                if(gameTable[i][j]==1) {
                    empty=false;
                    break;
                }
            }
            if (empty==true){
                return j;
            }
        }
        return -1;
    }
    
    private int[][] fillInStatus(gameBlock temp, int[][] gameTable, int x, int y) {
        int[][] tempBlock = temp.getBlockStructure();
        for (int i = 0; i < tempBlock.length; ++i) {
            for (int j = 0; j < tempBlock[i].length; ++j) {
                if (gameTable[i + x][j + y] == 0 && tempBlock[i][j] == 2) {
                    gameTable[i + x][j + y] = tempBlock[i][j];
                }
            }
        }
        return gameTable;
    }
    
        protected boolean checkForCollisionAi(int[][] tempBlock, int[][] gameTable, int tempX, int tempY) {
        clearMovingBlocks(tempBlock, 1);
        for (int i = 0; i < tempBlock.length; ++i) {
            for (int j = 0; j < tempBlock[i].length; ++j) {
                //Testing if the block is in the bounds of gamestatus
                if (i + tempX < gameTable.length && i + tempX > -1 && j + tempY < gameTable[0].length && tempX > -1) {
                    if (gameTable[i + tempX][j + tempY] == 1 && tempBlock[i][j] == 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
        
    /*
     * Method to turn all 2's into given integer
     */
    private void clearMovingBlocks(int[][] temp, int filler) {
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i].length; ++j) {
                if (temp[i][j] == 2) {
                    temp[i][j] = filler;
                }
            }
        }
    }
    
    private int[][] copyTable(int[][] temp) {
        int[][] copy = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i].length; ++j) {
                copy[i][j] = temp[i][j];
            }
        }
        return copy;
    }
}