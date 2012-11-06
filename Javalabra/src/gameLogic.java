/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jarno
 */
public class gameLogic {
    
    //Status-table for game, for every x,y co-ordinate the table has a int-value between 0 and 4,
    //0 is empty square, 1 is black square, 2 is black square that is falling down and 3 is moving square that can't fall anymore,
    //and it will be changed to 1.
    private int[][] status;
    //width and heigth of the tetris-game
    private int width;
    private int height;
    //Boolean to tell if there are moving blocks
    private boolean movingBlocks;
    private gameBlock currentBlock;

    
    public gameLogic() {
        this.width=12;
        this.height=15;
        //fill up this.status and this.rotations
        this.status = new int[this.width][this.height];
        fillTables(this.status);
        this.movingBlocks=false;
        
    }

    /*
     * Method to generate random block
     */
    
    private gameBlock generateBlock() {
        //code to generate random block
        return new gameBlock(4);
    }
        
    /*
     * Method for filling table
     */
    
    private void fillTables(int[][] temp) {
        for (int i=0;i<temp.length;++i){
            for (int j=0;j<temp[i].length;++j){
                temp[i][j]=0;
            }
        }
    }
    
    /*
     * Method for filling table
     */
    
    private void fillTables(int[][] temp, int oldNumber, int newNumber){
        for (int i=0;i<temp.length;++i){
            for(int j=0;j<temp.length;++j){
                if (temp[i][j]==oldNumber){
                    temp[i][j]=newNumber;
                }
            }
        }
    }
    
    
    /*
     * Method for updating game-status, usually bringing block down for one row
     */
    
    public void updateGame() {
        
        //Code to generate new block and to put it in game
        if(!this.movingBlocks){
            this.currentBlock=generateBlock();
            int[][] tempBlock=this.currentBlock.getBlockStructure();
            for (int i=0;i<tempBlock.length;++i){
                for (int j=0;j<tempBlock[i].length;++j){
                    if (this.status[i][j]==0){
                        this.status[i][j]=tempBlock[i][j];
                    }
                    else{
                        //Code when the game is lost
                    }
                }
            }
            this.movingBlocks=true;
        }
        
        //Code to check if block can be moved
        else{
            int[][] tempBlock=this.currentBlock.getBlockStructure();
            boolean tempBreak=true;
            for (int i=0;i<tempBlock.length;++i){
                for (int j=tempBlock[i].length-1;j>-1;--j){
                    //Checks if block can be moved down by one
                    if (tempBlock[i][j]==2){
                        //If checks if moved block is in the vertical bounds, done for all the blocks in current moving block
                        if (j+this.currentBlock.getBlockYco()<this.status[i].length-1 && this.status[this.currentBlock.getBlockXco()+i][this.currentBlock.getBlockYco()+j+1]!=1) {
                               //Currently analyzed block can be moved down
                        }
                        else {
                            tempBreak=false;
                            this.movingBlocks=false;
                            clearMovingBlocks(1);
                            break;
                        }
                    }
                }
            }
            //If all the squares in the block can be moved down
            if (tempBreak){
                moveBlockDown();
            }
        }
    }
    
    /*
     * Method to turn all 2's into given integer
     */
    
    private void clearMovingBlocks(int filler) {
        for (int i=0;i<this.status.length;++i){
            for (int j=0;j<this.status[i].length;++j) {
                if (this.status[i][j]==2){
                    this.status[i][j]=filler;
                }
            }
        }
    }
    
    /*
     * Method to move current block down by one move
     */
    
    private void moveBlockDown() {
        int[][] temp = this.currentBlock.getBlockStructure();
        for (int i=0;i<temp.length;++i){
            for (int j=temp[i].length-1;j>-1;--j){
                if (temp[i][j]==2){
                    this.status[i+this.currentBlock.getBlockXco()][j+this.currentBlock.getBlockYco()+1]=2;
                    this.status[i+this.currentBlock.getBlockXco()][j+this.currentBlock.getBlockYco()]=0;
                }
            }
        }
        this.currentBlock.addBlockYco(); 
    }

    /*
     * Method to rotate pieces
     */
    
    public void rotatePiece() {
        clearMovingBlocks(0);
        this.currentBlock.rotate();
        fillIn(this.currentBlock);
    }
    
    private void fillIn(gameBlock temp){
        int[][] tempBlock = temp.getBlockStructure();
        for (int i=0;i<tempBlock.length;++i){
            for (int j=0;j<tempBlock[i].length;++j) {
                this.status[i+temp.getBlockXco()][j+temp.getBlockYco()]=tempBlock[i][j];
            }
        }
    }
    
    public int[][] getGameStatus(){
        return this.status;
    }
}