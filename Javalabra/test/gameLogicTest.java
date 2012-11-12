/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarno
 */
public class gameLogicTest {
    
    public gameLogicTest() {
    }
    gameLogic testGame;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testGame = new gameLogic(); 
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
      public void gameLogicConstructorTester() {   
      int[][] temp = new int[12][15];
      assertEquals(temp ,  testGame.getGameStatus());
  }
    
    @Test
    public void gameLogicUpdateTest(){
        int[][] temp = testGame.getGameStatus();
        testGame.updateGame();
        int[][] temp2 = testGame.getGameStatus();
        assertEquals(temp.length,temp2.length);
    }
    
    @Test
    //Testing if row is eliminated correctly
    public void gameLogicRowEliminated(){
        for (int i=0;i<testGame.status.length;++i){
            testGame.status[i][testGame.status[0].length-1]=1;
        }
        assertEquals(testGame.updateGame(), 1);
    }
    
    @Test
    //Testing if game-status collides with block
    public void gameLogicCollisionTest(){
        for (int i=0;i<testGame.status.length;++i){
            testGame.status[i][testGame.status[0].length-1]=1;
        }
        gameBlock test = new gameBlock(1);
        test.setBlockYco(testGame.getGameStatus()[0].length-4);
        boolean collision = testGame.checkForCollision(test.getBlockStructure(), test.getBlockXco(), test.getBlockYco());
        assertEquals(collision, true);
    }
    
    @Test
    //Testing if game-status collides with block
    public void gameLogicCollisionTestTwo(){
        for (int i=0;i<testGame.status.length;++i){
            testGame.status[i][testGame.status[0].length-1]=1;
        }
        gameBlock test = new gameBlock(1);
        test.setBlockYco(testGame.getGameStatus()[0].length-5);
        boolean collision = testGame.checkForCollision(test.getBlockStructure(), test.getBlockXco(), test.getBlockYco());
        assertEquals(collision, false);
    }
}