package gameLogic;

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
    //0 is empty square, 1 is black square and 2 is black square that is falling down
    //and it will be changed to 1.
    protected int[][] status;
    //width and heigth of the tetris-game
    private int width;
    private int height;
    //Boolean to tell if there are moving blocks
    private boolean movingBlocks;
    private gameBlock currentBlock;

    public gameLogic() {
        this(12, 15);
    }

    /**
     * Creates gameLogic with given width and height
     *
     * @param width Width of tetris-game
     *
     * @param height height of tetris-game
     */

    public gameLogic(int width, int height) {
        this.width = width;
        this.height = height;
        //fill up this.status and this.rotations
        this.status = new int[this.width][this.height];
        fillTables(this.status);
        this.movingBlocks = false;
    }
    
    /**
     * Method for updating game-status, usually bringing block down for one row,
     * returns an int which tells how many rows were removed (0 is none), -1 is
     * returned if game is lost
     *
     * @return 0 or bigger tells how many rows were eliminated, -1 tells game over
     */
    public int updateGame() {

        //Code to generate new block and to put it in game
        if (!this.movingBlocks) {
            return updateGameNoMovingRows();
        } //Code to check if block can be moved
        else {
            return updateGameMovingRows();
        }
    }

    /**
     *
     * @return int[][]-table which has the current game, 0's are empty squares, 1's are solid blocks and 2's are currently moving blocks
     */
    
    public int[][] getGameStatus() {
        return this.status;
    }

    /**
     *
     * @return the x-coordinate of current moving blocks upper left corner
     */
    
    public int getBlockXco(){
        return this.currentBlock.getBlockXco();
    }

    /**
     *
     * @return the y-coordinate of current moving blocks upper left corner
     */
    public int getBlockYco(){
        return this.currentBlock.getBlockYco();
    }

    /**
     *
     * @return returns the type of block
     */
    public int getCurrentBlockType(){
        return this.currentBlock.getBlockType();
    }
    
    
    /**
     * Method to rotate or move pieces, 0 rotates piece, 1 moves piece left, 2
     * moves piece right
     */
    public void movePiece(int move) {
        int[][] temp = copyTable(this.status);
        clearMovingBlocks(this.status, 0);
        int[][] tempTable = this.currentBlock.getBlockStructure();
        int tempX = this.currentBlock.getBlockXco();
        int tempY = this.currentBlock.getBlockYco();
        if (move == 0) {
            rotatePiece(tempTable,temp,tempX,tempY);
        } else if (move == 1) {
            movePieceLeft(tempTable,temp,tempX,tempY);
        } else if (move == 2) {
            movePieceRight(tempTable,temp,tempX,tempY);
        }
    }
    
    /*
     * Method to generate random block
     */
    private gameBlock generateBlock() {
        //code to generate random block
        double random = Math.random() * 7;
        return new gameBlock((int) random + 1);
    }
    
    /*
     * Methods to rotate or move blocks
     */
    
    private void movePieceRight(int[][] tempTable, int[][] temp, int tempX, int tempY){
            if (!checkForCollision(tempTable, tempX - 1, tempY)) {
                this.currentBlock.subtractXco();
                fillIn(this.currentBlock);
            } else {
                this.status = temp;
            }
    }
    
    private void movePieceLeft(int[][] tempTable, int[][] temp, int tempX, int tempY){
            if (!checkForCollision(tempTable, tempX + 1, tempY)) {
                this.currentBlock.addXco();
                fillIn(this.currentBlock);
            } else {
                this.status = temp;
            }
    }
    
    private void rotatePiece(int[][] tempTable, int[][] temp, int tempX, int tempY) {
            tempTable = this.currentBlock.getNextRotationStructure();
            //Includes ad-hoc fix for bug with block type 4 being at the left edge of gamescreen and trying to rotate
            if (!checkForCollision(tempTable, tempX, tempY) && !(this.currentBlock.getRotationDegree()==3 && this.currentBlock.getBlockXco()==0)) {
                this.currentBlock.rotate();
                fillIn(this.currentBlock);
            } else {
                this.status = temp;
            }
    }

    /*
     * Method for filling table
     */
    private void fillTables(int[][] temp) {
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i].length; ++j) {
                temp[i][j] = 0;
            }
        }
    }

    /*
     * Method for filling table
     */
    private void fillTables(int[][] temp, int oldNumber, int newNumber) {
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp.length; ++j) {
                if (temp[i][j] == oldNumber) {
                    temp[i][j] = newNumber;
                }
            }
        }
    }
    
    /*
     * Method to update game status when there are no moving rows
     */

    private int updateGameNoMovingRows() {
        this.currentBlock = generateBlock();
        int[][] tempBlock = this.currentBlock.getBlockStructure();
        for (int i = 0; i < tempBlock.length; ++i) {
            for (int j = 0; j < tempBlock[i].length; ++j) {
                if (this.status[i][j] == 0) {
                    this.status[i][j] = tempBlock[i][j];
                } else {
                    //Code when the game is lost
                    return -1;
                }
            }
        }
        this.movingBlocks = true;
        return (checkFullRows());
    }
    
    /*
     * Method to update game when there are moving rows
     */
    
    private int updateGameMovingRows(){
            int[][] tempBlock = this.currentBlock.getBlockStructure();
            boolean tempBreak = true;
            for (int i = 0; i < tempBlock.length; ++i) {
                for (int j = tempBlock[i].length - 1; j > -1; --j) {
                    //Checks if block can be moved down by one
                    if (tempBlock[i][j] == 2) {
                        //If checks if moved block is in the vertical bounds, done for all the blocks in current moving block
                        if (j + this.currentBlock.getBlockYco() < this.status[i].length - 1 && this.status[this.currentBlock.getBlockXco() + i][this.currentBlock.getBlockYco() + j + 1] != 1) {
                            //Currently analyzed block can be moved down
                        } else {
                            tempBreak = false;
                            this.movingBlocks = false;
                            clearMovingBlocks(this.status, 1);
                            break;
                        }
                    }
                }
            }
            //If all the squares in the block can be moved down
            if (tempBreak) {
                moveBlockDown();
            }
            return 0;
    }

    private int checkFullRows() {
        int eliminatedRows = 0;
        int fullRowIndex = -1;
        for (int j = 0; j < this.status[0].length; ++j) {
            boolean fullRow = true;
            for (int i = 0; i < this.status.length; ++i) {
                if (this.status[i][j] != 1) {
                    fullRow = false;
                }
            }
            if (fullRow) {
                fullRowIndex = j;
                ++eliminatedRows;
            }
            eliminateFullRow(fullRowIndex);
        }
        return eliminatedRows;
    }

    /*
     * Method to eliminate full row, if index is -1, no row is eliminated
     */
    private void eliminateFullRow(int index) {
        if (index != -1) {
            for (int j = 0; j < this.status.length; ++j) {
                this.status[j][index] = 0;
            }
            bringStatusDown(index);
            //Method to add score here
        }
    }

    /*
     * Method to bring all the rows abowe eliminated row down by one
     */
    private void bringStatusDown(int index) {
        for (int i = 0; i < this.status.length; ++i) {
            for (int j = index; j > 0; --j) {
                this.status[i][j] = this.status[i][j - 1];
            }
        }
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

    /*
     * Method to move current block down by one move
     */
    private void moveBlockDown() {
        int[][] temp = this.currentBlock.getBlockStructure();
        for (int i = 0; i < temp.length; ++i) {
            for (int j = temp[i].length - 1; j > -1; --j) {
                if (temp[i][j] == 2) {
                    this.status[i + this.currentBlock.getBlockXco()][j + this.currentBlock.getBlockYco() + 1] = 2;
                    this.status[i + this.currentBlock.getBlockXco()][j + this.currentBlock.getBlockYco()] = 0;
                }
            }
        }
        this.currentBlock.addBlockYco();
    }


    /*
     * Method to copy table
     */
    private int[][] copyTable(int[][] temp) {
        int[][] copy = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i].length; ++j) {
                copy[i][j] = temp[i][j];
            }
        }
        return copy;
    }

    /*
     * Method for adding given block to the game-status
     */
    private boolean fillIn(gameBlock temp) {
        int[][] tempBlock = temp.getBlockStructure();
        for (int i = 0; i < tempBlock.length; ++i) {
            for (int j = 0; j < tempBlock[i].length; ++j) {
                if (this.status[i + temp.getBlockXco()][j + temp.getBlockYco()] == 0 && tempBlock[i][j] == 2) {
                    this.status[i + temp.getBlockXco()][j + temp.getBlockYco()] = tempBlock[i][j];
                }
            }
        }
        return true;
    }

    /*
     * Method to check if block collides with solid block or edges on
     * game-status
     */
    protected boolean checkForCollision(int[][] tempBlock, int tempX, int tempY) {
        clearMovingBlocks(tempBlock, 1);
        for (int i = 0; i < tempBlock.length; ++i) {
            for (int j = 0; j < tempBlock[i].length; ++j) {
                //Testing if the block is in the bounds of gamestatus
                if (i + tempX < this.status.length && i + tempX > -1 && j + tempY < this.status[0].length && tempX > -1) {
                    if (this.status[i + tempX][j + tempY] == 1 && tempBlock[i][j] == 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}