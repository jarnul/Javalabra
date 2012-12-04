package GameLogic;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import GameLogic.GameBlock;
import GameLogic.GameLogic;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author jarno
 */
@RunWith(JUnit4.class)
public class GameLogicTest {

    public GameLogicTest() {
    }
    GameLogic testGame;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        testGame = new GameLogic();
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
        assertEquals(temp, testGame.getGameStatus());
    }

    @Test
    public void gameLogicUpdateTest() {
        int[][] temp = testGame.getGameStatus();
        testGame.updateGame();
        int[][] temp2 = testGame.getGameStatus();
        assertEquals(temp.length, temp2.length);
    }

    @Test
    //Testing if row is eliminated correctly
    public void gameLogicRowEliminated() {
        for (int i = 0; i < testGame.status.length; ++i) {
            testGame.status[i][testGame.status[0].length - 1] = 1;
        }
        assertEquals(testGame.updateGame(), 1);
    }

    @Test
    //Testing if game-status collides with block
    public void gameLogicCollisionTest() {
        for (int i = 0; i < testGame.status.length; ++i) {
            testGame.status[i][testGame.status[0].length - 1] = 1;
        }
        GameBlock test = new GameBlock(1);
        test.setBlockYco(testGame.getGameStatus()[0].length - 4);
        boolean collision = testGame.checkForCollision(test.getBlockStructure(), test.getBlockXco(), test.getBlockYco());
        assertEquals(collision, true);
    }

    @Test
    //Testing if game-status collides with block
    public void gameLogicCollisionTestTwo() {
        for (int i = 0; i < testGame.status.length; ++i) {
            testGame.status[i][testGame.status[0].length - 1] = 1;
        }
        GameBlock test = new GameBlock(1);
        test.setBlockYco(testGame.getGameStatus()[0].length - 5);
        boolean collision = testGame.checkForCollision(test.getBlockStructure(), test.getBlockXco(), test.getBlockYco());
        assertEquals(collision, false);
    }

    @Test
    //Testing if eliminating multitple rows work
    public void gameLogicMultipleRowEliminated() {
        for (int i = 0; i < testGame.status.length; ++i) {
            testGame.status[i][testGame.status[0].length - 1] = 1;
        }
        for (int i = 0; i < testGame.status.length; ++i) {
            testGame.status[i][testGame.status[0].length - 2] = 1;
        }
        assertEquals(testGame.updateGame(), 2);
        int[][] temp = new int[testGame.getGameStatus().length][testGame.getGameStatus()[0].length];
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[0].length; ++j) {
                temp[i][j] = 0;
            }
        }
        int[][] testTable = testGame.getGameStatus();
        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[0].length - 5; ++j) {
                testTable[i][j] = 0;
            }
        }
        assertEquals(testTable, temp);
    }

    //Testing if moving too much left, too much right or rotating wildly causes errors
    @Test
    public void gameLogicMovementTests() {
        testGame.updateGame();
        try {
            for (int i = 0; i < 150; ++i) {
                int rand = (int) (Math.random() * 3 + 1);
                testGame.movePiece(rand);
                testGame.updateGame();
            }
            
        } catch (IndexOutOfBoundsException e) {
            fail( "indexes failed" );
        }
    }
    
    //Testing whether situation that is game over really ends in game-over
    @Test
    public void gameLogicGameOver(){
        int[][] testTable=testGame.getGameStatus();
        for (int i=0;i<testTable.length-1;++i){
            for (int j=0;j<testTable[0].length;++j){
                testTable[i][j]=1;
            }
        }
        assertEquals(testGame.updateGame(), -1);
    }
    
    @Test
    public void gameLogicMovePieceRightTest(){
        int[][] testTable=testGame.getGameStatus();
        int[][] testBlock=new GameBlock(1).getBlockStructure();
        try {
            testGame.movePieceRight(testTable, testBlock, 100, 100);
            testGame.movePieceRight(testTable, testBlock, -100, -100);   
        
        } catch (IndexOutOfBoundsException e) {
            fail( "indexes failed" );
        }
        
    }
    @Test
    public void gameLogicMovePieceLeftTest(){
        int[][] testTable=testGame.getGameStatus();
        int[][] testBlock=new GameBlock(1).getBlockStructure();
        try {
            testGame.movePieceLeft(testTable, testBlock, 100, 100);            
            testGame.movePieceLeft(testTable, testBlock, -100, -100);   
        } catch (IndexOutOfBoundsException e) {
            fail( "indexes failed" );
        }
    }
}