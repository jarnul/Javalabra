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
    
    public gameBlock() {
        this(1);
    }
    
    public gameBlock(int type) {
        this.blockType=type;
        this.blockStructure=buildBlock(type);
        this.xCoordinate=0;
        this.yCoordinate=0;
    }
    
    /*
     * Methods to create different blocks
     */
    
    private int[][] createLineBlock(){
         int[][] temp = new int[1][4];
         for (int i=0;i<4;++i){
            temp[0][i]=2;
         }
         return temp;
    }
    
    private int[][] createSquidBlock(){
         int[][] temp = new int[4][4];
         fillTable(temp);
         temp[0][0]=2;
         for (int i=0;i<temp[1].length;++i){
              temp[1][i]=2;
         }
         return temp;
    }
    
    private int[][] createSquareBlock(){
        int[][] temp = new int[2][2];
        fillTable(temp);
        for (int i=0;i<2;++i){
            for (int j=0;j<2;++j){
                   temp[i][j]=2;
            }
        }
        return temp;
    }
    
    private int[][] createPointyBlock(){
        int[][] temp = new int[3][2];
        fillTable(temp);
        temp[1][0]=2;
        for(int i=0;i<3;++i){
            temp[i][1]=2;
        }
        return temp;
    }
    
    private int[][] createPointyDownBlock(){
        int[][] temp = new int[3][2];
        fillTable(temp);
        temp[1][1]=2;
        for (int i=0;i<3;++i){
            temp[i][0]=2;
        }
        return temp;
    }
    
    
     /*
     * Method for generating different kinds of blocks, block * (-1) is the rotated block of a given block
     */
    
    private int[][] buildBlock(int type){
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
                return createPointyDownBlock();
            }

            default: {
                return null;
            }
        }
    }
    
    /*
     * Method to fill a int[][] with 0's
     */
    
    private void fillTable(int[][] temp){
        for (int i=0;i<temp.length;++i){
            for (int j=0;j<temp[i].length;++j){
                temp[i][j]=0;
            }
        }
    }
    
    /*
     * Method to rotate pieces
     */
    
    public void rotate(){
        //Creates the transpose-matrix of this.blockStructure
        int[][] rotated = new int[this.blockStructure[0].length][this.blockStructure.length];
        for (int i=0;i<rotated.length;++i){
            for (int j=0;j<rotated[i].length;++j){
                rotated[i][j]=this.blockStructure[j][i];
            }
        }
        this.blockStructure=rotated;
    }
    
    public int getBlockType(){
        return this.blockType;
    }
    
    public int getBlockXco(){
        return this.xCoordinate;
    }
    
    public int getBlockYco(){
        return this.yCoordinate;
    }
    
    public void setBlockXco(int x) {
        this.xCoordinate=x;
    }
    
    public void setBlockYco(int y) {
        this.yCoordinate=y;
    }
    
    public void addBlockYco(){
        ++this.yCoordinate;
    }
    
    public int[][] getBlockStructure() {
        return this.blockStructure;
    }
}
