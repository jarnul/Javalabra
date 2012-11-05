/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csguest
 */
public class gameLogic {
    
    //Status-table for game, for every x,y co-ordinate the table has a int-value between 0 and 4,
    //0 is empty square, 1 is black square, 2 is black square that is falling down and 3 is moving square that can't fall anymore,
    //and it will be changed to 1.
    private int[][] status;
    //For every x,y co-ordinate rotations-table includes how to rotate current block in x and y dimensions.
    private int[][][][] rotations;
    private int width;
    private int height;
    
    public gameLogic() {
        this.width=12;
        this.height=15;
        this.status = new int[this.width][this.height];
        for (int i=0;i<this.width;++i){
            for (int j=0;j<this.height;++j){
                this.status[i][j]=0;
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
            for(int j=0;j<this.status[i].length;++j){
                if (this.status[i][j]==2) {
                    //Code to tell block to fall down one row
                    if (this.status[i].length>j+1) {
                        //Code to handle if block underneath is empty, moving or solid
                        if (this.status[i][j+1]==0){
                            this.status[i][j]=0;
                            //Status is set to 3 indicating a block has moved to a co-ordinate during current actionlistener-update
                            this.status[i][j+1]=3;
                        }
                        else if (this.status[i][j+1]==1){
                            this.status[i][j]=1;
                        }
                        else {
                            this.status[i][j]=0;
                            this.status[i][j+1]=2;
                        }
                    }
                    else{
                        this.status[i][j]=1;
                    }
                    movingBlocks=true;
                }
                if (this.status[i][j]==3){
                    this.status[i][j]=2;
                }

            }
        }

        //Code to generate a new block
        if(!movingBlocks){
            this.status[0][0]=2;
        }
    }
    
    public int[][] getGameStatus(){
        return this.status;
    }
}
