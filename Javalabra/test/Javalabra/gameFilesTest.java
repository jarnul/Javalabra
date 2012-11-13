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
public class gameFilesTest {
    
    gameFiles testFile;
    
    public gameFilesTest() {
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
    @Test
    public void hello() {
        testFile = new gameFiles("test", 500);
        testFile.saveScore();
        assertEquals(500, testFile.readHighScore());
    }
}
