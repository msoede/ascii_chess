/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msoede
 */
public class MoveTest {
    
    public MoveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of compareMove method, of class Move.
     */
    @Test
    public void testCompareMove() {
        System.out.println("compareMove");
        Move move1 = new Move(1, 1, 0, 0, true, true, true, true, true, false);
        Move move2 = new Move(1, 1, 0, 0, true, true, true, true, true, true);
        Move move3 = new Move(0, 0, 1, 0, true, true, true, true, true, true);
        Move move4 = new Move(0, 0, 0, 2, false, true, true, true, true, true);

        assertEquals(move1.compareMove(move1), true);
        assertEquals(move1.compareMove(move2), false);
        assertEquals(move1.compareMove(move3), false);
        assertEquals(move1.compareMove(move4), false);
        assertEquals(move2.compareMove(move2), true);
    }
    
}
