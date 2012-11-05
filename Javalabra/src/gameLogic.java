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
    
    public gameLogic() {
        this.width=12;
        this.height=15;
        //fill up this.status and this.rotations
        this.status = new int[this.width][this.height];
        fillTables(this.status);
    }
    
    private void fillTables(int[][] temp) {
        for (int i=0;i<temp.length;++i){
            for (int j=0;j<temp[i].length;++j){
                temp[i][j]=0;
            }
        }
    }
    
    private void fillTables(int[][] temp, int oldNumber, int newNumber){
        for (int i=0;i<temp.length;++i){
            for(int j=0;j<temp.length;++j){
                if (temp[i][j]==oldNumber){
                    temp[i][j]=newNumber;
                }
            }
        }
    }
    
    
    public void updateGame() {
        //Check if there are blocks on the top row (losing condition)
        for (int i=0;i<this.status.length;++i){
            if (this.status[i][0]==1){
                //code when the game is lost
            }
        }
        

        //Check if there are blocks moving
        boolean movingBlocks=false;
        for (int i=0;i<this.status.length;++i){
            for(int j=this.status[i].length-1;j>-1;--j){
                if (this.status[i][j]==2) { //if block is moving
                    //Code to tell block to fall down one row
                    if (this.status[i].length>j+1) {
                        //Code to handle if block underneath is empty, moving or solid
                        if (this.status[i][j+1]==0){
                            //Block underneath is empty, so mark block there
                            this.status[i][j]=0;
                            this.status[i][j+1]=2;
                        }
                        //Here block underneath is solid
                        else {
                            this.status[i][j]=1;
                        }
                        
                    }
                    //If the moving block is at the bottom
                    else{
                        this.status[i][j]=1;
                    }
                    
                    movingBlocks=true;
                }
            }
        }
        

        //Code to generate a new block
        if(!movingBlocks){
            this.status[5][6]=2;
            this.status[5][5]=2;
        }
    }

    //Code to rotate moving pieces
    public void rotateRight(){
        boolean firstMove=false;
        int coordinateFix=0;
        for (int i=0;i<this.status.length;++i){
            for (int j=0;j<this.status[i].length;++j){
                //Rotation is done by switching around the co-ordinates and moving the piece
                if (this.status[i][j]==2 && i>j){
                     if(!firstMove){
                         firstMove=true;
                         coordinateFix=Math.abs(i-j);
                     }
                     this.status[j+coordinateFix-1][i-coordinateFix]=3;
                     this.status[i][j]=0;
                }
                else if (this.status[i][j]==2 && j>i){
                    if(!firstMove){
                         firstMove=true;
                         coordinateFix=Math.abs(i-j);
                     }
                    this.status[j-coordinateFix-1][i+coordinateFix]=3;
                    this.status[i][j]=0;
                }
                /*else if (this.status[i][j]==2 && j==i){
                    if(firstMove){
                        firstMove=true;
                        coordinateFix=0;
                    }
                }*/
            }
        }
        fillTables(this.status,3,2);
    }
    
    public int[][] getGameStatus(){
        return this.status;
    }
}
