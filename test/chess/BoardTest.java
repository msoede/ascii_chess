/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
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
public class BoardTest {
    
    public BoardTest() {
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
     * Test of isHumanPlayer method, of class Board.
     */
    @Test
    public void testIsHumanPlayer() {
        System.out.println("isHumanPlayer");
        Board instance = new Board("White", "Black");
        boolean expResult = true;
        boolean result = instance.isHumanPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHumanPlayer method, of class Board.
     */
    @Test
    public void testSetHumanPlayer() {
        System.out.println("setHumanPlayer");
        String humanPlayerSide = "";
        Board instance = null;
        instance.setHumanPlayer(humanPlayerSide);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSide method, of class Board.
     */
    @Test
    public void testIsSide() {
        System.out.println("isSide");
        Board instance = null;
        boolean expResult = false;
        boolean result = instance.isSide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSide method, of class Board.
     */
    @Test
    public void testSetSide() {
        System.out.println("setSide");
        boolean side = false;
        Board instance = null;
        instance.setSide(side);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoveHistory method, of class Board.
     */
    @Test
    public void testGetMoveHistory() {
        System.out.println("getMoveHistory");
        Board instance = null;
        ArrayList<Move> expResult = null;
        ArrayList<Move> result = instance.getMoveHistory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMoveHistory method, of class Board.
     */
    @Test
    public void testSetMoveHistory() {
        System.out.println("setMoveHistory");
        ArrayList<Move> moveHistory = null;
        Board instance = null;
        instance.setMoveHistory(moveHistory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchTime method, of class Board.
     */
    @Test
    public void testGetSearchTime() {
        System.out.println("getSearchTime");
        Board instance = null;
        int expResult = 0;
        int result = instance.getSearchTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSearchTime method, of class Board.
     */
    @Test
    public void testSetSearchTime() {
        System.out.println("setSearchTime");
        int searchTime = 0;
        Board instance = null;
        instance.setSearchTime(searchTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchDepth method, of class Board.
     */
    @Test
    public void testGetSearchDepth() {
        System.out.println("getSearchDepth");
        Board instance = null;
        int expResult = 0;
        int result = instance.getSearchDepth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSearchDepth method, of class Board.
     */
    @Test
    public void testSetSearchDepth() {
        System.out.println("setSearchDepth");
        int searchDepth = 0;
        Board instance = null;
        instance.setSearchDepth(searchDepth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of switchSide method, of class Board.
     */
    @Test
    public void testSwitchSide() {
        System.out.println("switchSide");
        Board instance = null;
        instance.switchSide();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSide method, of class Board.
     */
    @Test
    public void testGetSide() {
        System.out.println("getSide");
        Board instance = null;
        boolean expResult = false;
        boolean result = instance.getSide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPiece method, of class Board.
     */
    @Test
    public void testGetPiece() {
        System.out.println("getPiece");
        int i = 0;
        int j = 0;
        Board instance = null;
        Piece expResult = null;
        Piece result = instance.getPiece(i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPiece method, of class Board.
     */
    @Test
    public void testSetPiece() {
        System.out.println("setPiece");
        int i = 0;
        int j = 0;
        Piece piece = null;
        Board instance = null;
        instance.setPiece(i, j, piece);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class Board.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Board instance = null;
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of undoLastMove method, of class Board.
     */
    @Test
    public void testUndoLastMove() {
        System.out.println("undoLastMove");
        Board instance = null;
        instance.undoLastMove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeMove method, of class Board.
     */
    @Test
    public void testMakeMove_Move() {
        System.out.println("makeMove");
        Move move = null;
        Board instance = null;
        instance.makeMove(move);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeMove method, of class Board.
     */
    @Test
    public void testMakeMove_4args() {
        System.out.println("makeMove");
        int n1 = 0;
        int l1 = 0;
        int n2 = 0;
        int l2 = 0;
        Board instance = null;
        boolean expResult = false;
        boolean result = instance.makeMove(n1, l1, n2, l2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printBoard method, of class Board.
     */
    @Test
    public void testPrintBoard() {
        System.out.println("printBoard");
        Board instance = null;
        instance.printBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
