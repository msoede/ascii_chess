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
     * Test of undoLastMove method, of class Board.
     */
    @Test
    public void testUndoLastMove() {
        System.out.println("undoLastMove");
        Board board = new Board("White", "Black");
        MoveGen moveGen = new MoveGen();
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        for (Move childMove : moveList) {
            board.makeMove(childMove);
            board.switchSide();
            ArrayList<Move> moveList2 = moveGen.generateAllMoves(board);
            for (Move childMove2 : moveList2) {
                board.makeMove(childMove2);
                board.printBoard();
                board.undoLastMove();
            }
            board.switchSide();
            board.printBoard();
            board.undoLastMove();
            //board.printBoard();
        }
    }
}
