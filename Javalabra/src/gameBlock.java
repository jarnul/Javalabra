/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
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

    public gameBlock(int type) {
        this.blockType = type;
        this.blockStructure = buildBlock(type);
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.rotationDegree = 0;
    }

    public gameBlock(gameBlock copy) {
        this(copy.getBlockType());
        this.xCoordinate = copy.getBlockXco();
        this.yCoordinate = copy.getBlockYco();
        this.rotationDegree = copy.getRotationDegree();
    }

    /*
     * Methods to create different blocks
     */
    private int[][] createLineBlock() {
        int[][] temp = new int[1][11];
        for (int i = 0; i < 11; ++i) {
            temp[0][i] = 2;
        }
        return temp;
    }

    private int[][] createSquidBlock() {
        int[][] temp = new int[2][4];
        fillTable(temp);
        temp[0][3] = 2;
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
        temp[0][0] = 2;
        temp[1][0] = 2;
        temp[1][1] = 2;
        temp[2][1] = 2;
        return temp;
    }

    private int[][] createReverseSquidBlock() {
        int[][] temp = new int[2][4];
        fillTable(temp);
        temp[1][3] = 2;
        for (int i = 0; i < temp[1].length; ++i) {
            temp[0][i] = 2;
        }
        return temp;
    }

    /*
     * Method for generating different kinds of blocks, block * (-1) is the
     * rotated block of a given block
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
    private int[][] matrixTranspose(int[][] temp) {
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
     * Methods to fix rotation degrees for special blocks
     */
    private void rotationDegreeEven() {
        if (this.blockType == 4) {
            if (this.rotationDegree == 2) {
                ++this.xCoordinate;
                this.rotationDegree = 3;
            } else {
                this.rotationDegree = 1;
            }
        }
    }

    private void rotationDegreeOdd() {
        if (this.blockType == 4) {
            if (this.rotationDegree == 1) {
                ++this.yCoordinate;
                this.rotationDegree = 2;
            } else {
                --this.xCoordinate;
                this.rotationDegree = 0;
            }
        }
    }

    /*
     * Method to rotate pieces using matrix-transposes
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

    /*
     * Method to return next rotation for inspection
     */
    public int[][] getNextRotationStructure() {
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
        return rotated;
    }

    public int getBlockType() {
        return this.blockType;
    }

    public int getBlockXco() {
        return this.xCoordinate;
    }

    public int getBlockYco() {
        return this.yCoordinate;
    }

    public void setBlockXco(int x) {
        this.xCoordinate = x;
    }

    public void setBlockYco(int y) {
        this.yCoordinate = y;
    }

    public void addBlockYco() {
        ++this.yCoordinate;
    }

    public int getRotationDegree() {
        return this.rotationDegree;
    }

    public int[][] getBlockStructure() {
        return copyTable(this.blockStructure);
    }

    public void subtractXco() {
        if (this.xCoordinate > 0) {
            --this.xCoordinate;
        }
    }

    public void addXco() {
        ++this.xCoordinate;
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
