package Javalabra;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jzvaris
 */
public class GameFilesTest {
    
    GameFiles testFile;
    
    public GameFilesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
//    @Test
//    public void gameFilesReadIfScoresFileDisappeared(){
//        //Delete the scores.txt file in here, currently by hand
//        testFile = new gameFiles("test", 0);
//        assertEquals(testFile.readHighScore(), -1);
//        testFile.saveScore();
//    }
        
    @Test
    public void gameFilesWriteAndReadFromFile() {
        testFile = new GameFiles("test", 500);
        testFile.saveScore();
        assertEquals(500, testFile.readHighScore());
    }
}
