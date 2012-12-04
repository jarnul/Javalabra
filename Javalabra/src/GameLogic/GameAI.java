/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

/**
 *The classfile that includes the crude AI
 * @author jzvaris
 */
public class GameAI {

    private GameLogic currentGame;
    private int numberOfRotations;

    /**
     * 
     * @param currentGame creates gameAI-object to handle given gameLogic-object
     */
    public GameAI(GameLogic currentGame) {
        this.currentGame = currentGame;
        this.numberOfRotations = 0;
    }

    /**
     * Method that moves block to desired spot using AI
     */
    public void updateGame() {
        if (this.currentGame.getBlockYco() > 0) {
            int temp = 0;
            int goalX = tryAllSpots(new GameBlock(this.currentGame.getCurrentBlockType()));
            while (goalX != this.currentGame.getBlockXco() && temp < this.currentGame.getGameStatus().length && this.currentGame.getBlockYco() < 2) {
                if (goalX < this.currentGame.getBlockXco()) {
                    this.currentGame.movePiece(2);
                } else if (goalX > this.currentGame.getBlockXco()) {
                    this.currentGame.movePiece(1);
                }
                ++temp;
            }
            this.currentGame.updateGame();
        } else {
            tryAllSpots(new GameBlock(this.currentGame.getCurrentBlockType()));
            while (this.numberOfRotations > 0) {
                this.currentGame.movePiece(0);
                --this.numberOfRotations;
            }
            this.currentGame.updateGame();
        }
    }

    /*
     * Method to tell if there are moving blocks on given gametable
     */
    private boolean movingBlocks(int[][] gameTable) {
        for (int i = 0; i < gameTable.length; ++i) {
            for (int j = 0; j < gameTable[0].length; ++j) {
                if (gameTable[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Method to try block in all possible spots
     */
    private int tryAllSpots(GameBlock testBlock) {
        int upperlimit = highestX(this.currentGame.getGameStatus());
        int lowest = 0;
        int xCo = 0;
        int[][] gameTableNoExtraMovingBlock = clearMovingBlocks(copyTable(this.currentGame.getGameStatus()), 0);
        for (int i = 0; i < this.currentGame.getGameStatus().length; ++i) {
            for (int j = this.currentGame.getGameStatus()[0].length - 1; j > upperlimit - 1; --j) {
                for (int k = 0; k < 4; ++k) {
                    if (!checkForCollisionAi(testBlock.getBlockStructure(), gameTableNoExtraMovingBlock, i, j) && canFreeFall(testBlock.getBlockStructure(), gameTableNoExtraMovingBlock, i, upperlimit, j)) {
                        if (lowest < j) {
                            lowest = j;
                            xCo = i;
                            this.numberOfRotations = k;
                        }
                    }
                    if (testBlock.getBlockType() != 4) {
                        testBlock.rotate();
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
            boolean empty = true;
            for (int i = 0; i < gameTable.length; ++i) {
                if (gameTable[i][j] == 1 || gameTable[i][j] == 2) {
                    empty = false;
                    break;
                }
            }
            if (empty == true) {
                return j;
            }
        }
        return -1;
    }

    /*
     * Method to add gameBlock to given gameTable at coordinates x and y of gametable
     */
    private int[][] fillInStatus(GameBlock temp, int[][] gameTable, int x, int y) {
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

    /*
     * Method to tell if given block can fall from given coordinates to given height
     */
    private boolean canFreeFall(int[][] tempBlock, int[][] gameTable, int x, int y, int targetHeight) {
        try {
            for (int i = 0; i < tempBlock.length; ++i) {
                for (int j = y; j < targetHeight + 1; ++j) {
                    if (gameTable[x + i][j] == 1) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /*
     * Method to check if there are collisions on given gametable with adding given block to given x and y co-ordinates
     */
    protected boolean checkForCollisionAi(int[][] tempBlock, int[][] gameTable, int tempX, int tempY) {
        clearMovingBlocks(tempBlock, 1);
        try {
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
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
        clearSolidBlocks(tempBlock, 2);
        return false;
    }

    /*
     * Method to turn all 2's into given integer
     */
    private int[][] clearMovingBlocks(int[][] temp, int filler) {
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i].length; ++j) {
                if (temp[i][j] == 2) {
                    temp[i][j] = filler;
                }
            }
        }
        return temp;
    }

    /*
     * Method to change cells of value 1 to given value in int[][]-table
     */
    private void clearSolidBlocks(int[][] temp, int filler) {
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[0].length; ++j) {
                if (temp[i][j] == 1) {
                    temp[i][j] = 2;
                }
            }
        }
    }

    /*
     * returns copied version of given int[][]-table
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