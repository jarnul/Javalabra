package GameLogic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import GameLogic.GameBlock;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author jzvaris
 */
public class GameBlockTest {
    
    GameBlock testBlock;
    
    public GameBlockTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testBlock = new GameBlock();
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
        testBlock = new GameBlock(5);
        int[][] tempBlock=testBlock.getNextRotationStructure();
        int[][] temp = new int[2][3];
        temp[1][0]=2;
        temp[0][1]=2;
        temp[1][1]=2;
        temp[0][2]=2;
        assertEquals(tempBlock, temp);
    }
    
    
    //Method to test rotationdegree for block 4
    @Test
    public void gameBlockRotationDegreeTest4(){
        testBlock=new GameBlock(4);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 1);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 2);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 3);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
    }
    //Method to test rotationdegree for block 5
    
    @Test
    public void gameBlockRotationDegreeTest5(){
        testBlock=new GameBlock(5);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
    }
        //Method to test rotationdegree for block 6
    @Test
    public void gameBlockRotationDegreeTest6(){
        testBlock=new GameBlock(6);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 1);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 1);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
    }
        //Method to test rotationdegree for block 7
    @Test
    public void gameBlockRotationDegreeTest7(){
        testBlock=new GameBlock(7);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeEven();
        assertEquals(testBlock.getRotationDegree(), 0);
        testBlock.rotationDegreeOdd();
        assertEquals(testBlock.getRotationDegree(), 0);
    }
    
    @Test
    public void gameBlockMatrixTranspose(){
        testBlock=new GameBlock(1);
        int[][] normalStruct=testBlock.matrixTranspose(testBlock.getBlockStructure());
        int[][] transpose=new int[4][1];
        for (int i=0;i<4;++i){
            transpose[i][0]=2;
        }
        assertEquals(normalStruct, transpose);
    }
    @Test
    public void gameBlockMatrixTransposeSkewed(){
        testBlock=new GameBlock(1);
        int[][] normalStruct=testBlock.matrixTranspose(testBlock.getBlockStructure());
        int[][] transpose=new int[4][1];
        for (int i=0;i<4;++i){
            transpose[i][0]=2;
        }
        assertEquals(normalStruct, transpose);
    }
}