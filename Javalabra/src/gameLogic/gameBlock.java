package gameLogic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Class for representing moving block. Normal rotate is done with rotate(), and other methods give means to edit this blocks x and y -coordinates.
 * Moving the block and filling it in game is left for gameLogic-object.
 * @author jarno
 */
public class gameBlock {

    private int blockType; //0=line-block etc.
    private int[][] blockStructure;
    private int xCoordinate;
    private int yCoordinate;
    private int rotationDegree;

    public gameBlock() {
        this(1);
    }
    
    /**
     * 
     * @param type int to tell which kind of block to build: 0 is lineblock, 2 is Squidblock, 3 is squareblock, 4 is pointyblock, 5 is snakeblock, 6 is reversesquidblock, 7 is reversesnakeblock
     */

    public gameBlock(int type) {
        this.blockType = type;
        this.blockStructure = buildBlock(type);
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.rotationDegree = 0;
    }

    
    /**
     * 
     * @param copy another gameblock-instance, which will be copied
     */
    public gameBlock(gameBlock copy) {
        this(copy.getBlockType());
        this.xCoordinate = copy.getBlockXco();
        this.yCoordinate = copy.getBlockYco();
        this.rotationDegree = copy.getRotationDegree();
    }
    
    /**
     * Method to rotate piece using matrix-transposes
     */
    public void rotate() {
        //Creates the transpose-matrix of this.blockStructure
        int[][] rotated;
        //rotationDegree is used in case block must be transposed from different origins (as with pointyBlock for example)
        if (this.rotationDegree == 0 || this.rotationDegree == 2) {
            rotated = matrixTranspose(this.blockStructure);
            //Special cases for pointyblocks and squidblocks
            rotationDegreeEven();

        } else {
            rotated = matrixTransposeSkewed(this.blockStructure);
            rotationDegreeOdd();
        }
        this.blockStructure = rotated;
    }

    /**
     * Method to return next rotation for inspection
     * 
     * @return returns the int[][] table of the piece that given piece would be if it was rotated
     */
    public int[][] getNextRotationStructure() {
        int[][] rotated;
        int tempRotation=this.rotationDegree;
        int tempXco=this.xCoordinate;
        int tempYco=this.yCoordinate;
        //rotationDegree is used in case block must be transposed from different origins (as with pointyBlock for example)
        if (this.rotationDegree == 0 || this.rotationDegree == 2) {
            rotated = matrixTranspose(this.blockStructure);
            //Special cases for pointyblocks and squidblocks
            rotationDegreeEven();

        } else {
            rotated = matrixTransposeSkewed(this.blockStructure);
            rotationDegreeOdd();
        }
        this.rotationDegree=tempRotation;
        this.xCoordinate=tempXco;
        this.yCoordinate=tempYco;
        return rotated;
    }

    
    /**
     * 
     * @return blocktype of this block
     */
    public int getBlockType() {
        return this.blockType;
    }
    
    /**
     * 
     * @return the x-coordinate of this block
     */

    public int getBlockXco() {
        return this.xCoordinate;
    }

    
    /**
     * 
     * @return the y-coordinate of this block
     */
    public int getBlockYco() {
        return this.yCoordinate;
    }
    
    /**
     * 
     * @param x the x-coordinate wanted for block
     */

    public void setBlockXco(int x) {
        this.xCoordinate = x;
    }
    /**
     * 
     * @param y the y-coordinate wanted for block
     */

    public void setBlockYco(int y) {
        this.yCoordinate = y;
    }
    
    /**
     * adds one to this blocks y-coordinate
     */

    public void addBlockYco() {
        ++this.yCoordinate;
    }
    /**
     * 
     * @return returns this blocks rotationdegree
     */

    public int getRotationDegree() {
        return this.rotationDegree;
    }
    /**
     * 
     * @return returns the int[][] table of current block
     */

    public int[][] getBlockStructure() {
        return copyTable(this.blockStructure);
    }
    
    /**
     * removes one from blocks current x-coordinate
     */

    public void subtractXco() {
        if (this.xCoordinate > 0) {
            --this.xCoordinate;
        }
    }
    
    /**
     * adds one to blocks current x-coordinate
     */

    public void addXco() {
        ++this.xCoordinate;
    }
    
    /*
     * Method for generating different kinds of blocks, block * (-1) is the
     * rotated block of a given block
     * 0 is lineblock, 2 is Squidblock, 3 is squareblock, 4 is pointyblock, 5 is snakeblock, 6 is reversesquidblock, 7 is reversesnakeblock
     */
    private int[][] buildBlock(int type) {
        switch (type) {

            case 1: {
                return createLineBlock();
            }
            case 2: {
                return createSquidBlock();
            }
            case 3: {
                return createSquareBlock();
            }
            case 4: {
                return createPointyBlock();
            }
            case 5: {
                return createSnakeBlock();
            }
            case 6: {
                return createReverseSquidBlock();
            }
            case 7: {
                return createReverseSnakeBlock();
            }

            default: {
                return null;
            }
        }
    }

    /*
     * Methods to create different blocks
     */
    private int[][] createLineBlock() {
        int[][] temp = new int[1][4];
        fillTable(temp);
        for (int i = 0; i < 4; ++i) {
            temp[0][i] = 2;
        }
        return temp;
    }

    private int[][] createSquidBlock() {
        int[][] temp = new int[2][3];
        fillTable(temp);
        temp[0][2] = 2;
        for (int i = 0; i < temp[1].length; ++i) {
            temp[1][i] = 2;
        }
        return temp;
    }

    private int[][] createSquareBlock() {
        int[][] temp = new int[2][2];
        fillTable(temp);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                temp[i][j] = 2;
            }
        }
        return temp;
    }

    private int[][] createPointyBlock() {
        int[][] temp = new int[3][2];
        fillTable(temp);
        temp[1][0] = 2;
        for (int i = 0; i < 3; ++i) {
            temp[i][1] = 2;
        }
        return temp;
    }

    private int[][] createSnakeBlock() {
        int[][] temp = new int[3][2];
        fillTable(temp);
        temp[0][1] = 2;
        temp[1][0] = 2;
        temp[1][1] = 2;
        temp[2][0] = 2;
        return temp;
    }

    private int[][] createReverseSnakeBlock() {
        int[][] temp = new int[3][2];
        fillTable(temp);
        temp[0][0] = 2;
        temp[1][0] = 2;
        temp[1][1] = 2;
        temp[2][1] = 2;
        return temp;
    }

    private int[][] createReverseSquidBlock() {
        int[][] temp = new int[2][3];
        fillTable(temp);
        temp[1][2] = 2;
        for (int i = 0; i < temp[1].length; ++i) {
            temp[0][i] = 2;
        }
        return temp;
    }

    /*
     * Method to fill a int[][]-table with 0's
     */
    private void fillTable(int[][] temp) {
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i].length; ++j) {
                temp[i][j] = 0;
            }
        }
    }

    /*
     * Method for regular Matrix Transpose
     */
    protected int[][] matrixTranspose(int[][] temp) {
        int[][] rotated = new int[temp[0].length][temp.length];
        for (int i = 0; i < rotated.length; ++i) {
            for (int j = 0; j < rotated[i].length; ++j) {
                rotated[i][j] = temp[j][i];
            }
        }
        return rotated;
    }

    /*
     * Method for Matrix transpose about different origin
     */
    private int[][] matrixTransposeSkewed(int[][] temp) {
        int[][] rotated = new int[temp[0].length][temp.length];
        for (int i = 0; i < rotated.length; ++i) {
            for (int j = 0; j < rotated[i].length; ++j) {
                rotated[rotated.length - i - 1][rotated[i].length - j - 1] = this.blockStructure[j][i];
            }
        }
        return rotated;
    }
    
    /*
     * Method to mirror block
     */
    
    private int[][] mirrorBlockXaxis(){
        int[][] temp = new int[this.blockStructure.length][this.blockStructure[0].length];
        for (int i=0;i<temp.length;++i){
            for (int j=0;j<temp[i].length;++j){
                temp[i][j] = this.blockStructure[this.blockStructure.length-1-i][j];
            }
        }
        return temp;
    }
    
    private int[][] mirrorBlockYaxis(){
        int[][] temp = new int[this.blockStructure.length][this.blockStructure[0].length];
        for (int i=0;i<temp.length;++i){
            for (int j=0;j<temp[i].length;++j){
                temp[i][j] = this.blockStructure[i][this.blockStructure[0].length-1-j];
            }
        }
        return temp;
    }
    

    /*
     * Methods to fix rotation degrees for special blocks (of type 4, 2, 5, 6 or 7)
     */
    
    
    protected void rotationDegreeEven() {
        if (this.blockType == 4) {
            if (this.rotationDegree == 2) {
                ++this.xCoordinate;
                this.rotationDegree = 3;
            } else {
                this.rotationDegree = 1;
            }

        } else if (this.blockType == 2 || this.blockType == 6) {
            this.rotationDegree = 1;
            this.blockStructure=mirrorBlockXaxis(); 
        }
        else if (this.blockType == 5 || this.blockType == 7){
            this.blockStructure=mirrorBlockYaxis();
        }
    }
    
    protected void rotationDegreeOdd() {
        if (this.blockType == 4) {
            if (this.rotationDegree == 1) {
                ++this.yCoordinate;
                this.rotationDegree = 2;
            } else {
                --this.xCoordinate;
                this.rotationDegree = 0;
            }
        } else if (this.blockType == 2 || this.blockType == 6) {
            this.rotationDegree = 0;
            this.blockStructure=mirrorBlockYaxis();
        }
    }
    
    /*
     * Method to create real copy from java-table
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
}
