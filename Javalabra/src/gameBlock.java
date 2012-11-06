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
     * Method for generating different kinds of blocks, block * (-1) is the rotated block of a given block
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
            case 3: {
                temp = new int[2][2];
                fillTable(temp);
                for (int i=0;i<2;++i){
                    for (int j=0;j<2;++j){
                        temp[i][j]=2;
                    }
                }
                return temp;
            }
            case -3: {
                return buildBlock(3);
            }
            case 4: {
                temp = new int[3][2];
                fillTable(temp);
                temp[1][0]=2;
                for(int i=0;i<3;++i){
                    temp[i][1]=2;
                }
                return temp;
            }
            case -4:{
                temp = new int[2][3];
                fillTable(temp);
                temp[0][1]=2;
                for (int i=0;i<3;++i){
                    temp[1][i]=2;
                }
                return temp;
            }
            case 5: {
                temp = new int[3][2];
                fillTable(temp);
                temp[1][1]=2;
                for (int i=0;i<3;++i){
                    temp[i][0]=2;
                }
                return temp;
            }
            case -5: {
                temp = new int[2][3];
                fillTable(temp);
                temp[1][1]=2;
                for (int i=0;i<3;++i){
                    temp[0 ][i]=2;
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
    
    /*
     * Method to rotate pieces
     */
    
    public void rotate(){
        int[][] rotated = new int[this.blockStructure[0].length][this.blockStructure.length];
        for (int i=0;i<rotated.length;++i){
            for (int j=0;j<rotated[i].length;++j){
                rotated[i][j]=this.blockStructure[j][i];
            }
        }
        this.blockStructure=rotated;
    }
    
//    public void rotate() {
//        if(this.blockType!=-4 && this.blockType!=-5){
//            this.blockStructure=buildBlock(-1*this.blockType);
//            this.blockType=-1*this.blockType;
//        }
//        else if (this.blockType==-4){
//            this.blockStructure=buildBlock(5);
//            this.blockType=5;
//        }
//        else {
//            this.blockStructure=buildBlock(4);
//            this.blockType=4;
//        }
//    }
    
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
