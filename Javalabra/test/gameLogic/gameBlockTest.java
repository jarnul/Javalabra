package gameLogic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author jzvaris
 */
public class gameBlockTest {
    
    gameBlock testBlock;
    
    public gameBlockTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testBlock = new gameBlock();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void gameBlockGetXtester() {
        assertEquals(testBlock.getBlockXco(), 0);
    }
    
    @Test
    public void gameBlockGetYtester() {
        assertEquals(testBlock.getBlockYco(), 0);
    }
    
    @Test
    public void gameBlockGetXtesterTwo() {
        testBlock.addBlockYco();
        assertEquals(testBlock.getBlockYco(), 1);
    }
    
    @Test
    public void gameBlockGetXtesterThree() {
        testBlock.setBlockXco(5);
        assertEquals(testBlock.getBlockXco(), 5);
    }
    
    @Test
    public void gameBlockGetYtesterTwo() {
        testBlock.setBlockYco(10);
        assertEquals(testBlock.getBlockYco(), 10);
    }
    
    @Test
      public void gameBlockConstructorTester() {   
      int[][] temp = new int[1][4];
      for (int i=0;i<4;++i){
             temp[0][i]=2;
      }
      assertEquals(temp ,  testBlock.getBlockStructure());
  }
    @Test
    public void gameBlockTypeTest(){
        assertEquals(testBlock.getBlockType(), 1);
    }
    
    @Test
    public void gameBlockTypeTestTwo(){
        testBlock.rotate();
        assertEquals(testBlock.getBlockType(), 1);
    }
    
    @Test
    public void gameBlockRotateTestBlock5(){
        testBlock = new gameBlock(5);
        int[][] tempBlock=testBlock.getNextRotationStructure();
        int[][] temp = new int[2][3];
        temp[1][0]=2;
        temp[0][1]=2;
        temp[1][1]=2;
        temp[0][2]=2;
        assertEquals(tempBlock, temp);
    }
}