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
     * Method for generating different kinds of blocks
     */
    
    private int[][] buildBlock(int type){
        int[][] temp;
        switch (type) {
        
            case 1: {
                temp = new int[1][4];
                for (int i=0;i<4;++i){
                    temp[0][i]=2;
                }
                return temp;
            }
            case -1: {
                temp = new int[4][1];
                for (int i=0;i<temp.length;++i){
                    temp[i][0]=2;
                }
                return temp;
            }
            case 2: {
                temp = new int[4][4];
                fillTable(temp);
                temp[0][0]=2;
                for (int i=0;i<temp[1].length;++i){
                    temp[1][i]=2;
                }
                return temp;
            
            }
            case -2: {
                temp = new int[4][4];
                fillTable(temp);
                temp[3][2]=2;
                for (int i=0;i<4;++i){
                    temp[i][3]=2;
                } 
                return temp;
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
    
    public void rotate() {
        this.blockStructure=buildBlock(-1*this.blockType);
        this.blockType=-1*this.blockType;
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
