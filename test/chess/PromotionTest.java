/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Objects.Board;
import chess.Objects.Fen;
import chess.Objects.Move;
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
public class PromotionTest {

    Board board;
    Fen fen;
    MoveGen moveGen;

    public PromotionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        moveGen = new MoveGen();
        fen = new Fen();
        board = new Board();
        board.setStartPosistion();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWhitePawnPromotion() {
        String fenString = "8/3P2p1/8/8/8/8/8/8 w - - 0 1";
        int d = 1; //exp resualt
        int r = 0; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();
        assertEquals(d, r);
        System.out.println("Board before promotion move to make: " + moveList.get(0).toString());
        board.printBoard();
        System.out.println("borad.side: " + board.isSide());
        board.makeMove(moveList.get(0));
        System.out.println("Move to make: " + moveList.get(0).toString());
        System.out.println("borad.side: " + board.isSide());
        System.out.println("Board after promotion");
        board.printBoard();
        char queenLocation = board.getPiece(7, 3).getType();
        assertEquals(queenLocation, 'Q');
        System.out.println("testPawnWhitePromotion passed");

        //undo the promotion move
        board.undoLastMove();
        board.printBoard();
        char pawnLocation = board.getPiece(6, 3).getType();
        assertEquals(pawnLocation, 'P');

    }

    @Test
    public void testBlackPawnPromotion() {
        String fenString = "8/8/8/8/8/8/3p2P1/8 b - - 0 1";
        int d = 1; //exp resualt
        int r; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();

        System.out.println("Board before promotion move to make: " + moveList.get(0).toString());
        board.printBoard();
        assertEquals(d, r);
        board.makeMove(moveList.get(0));
        System.out.println("after promotion is done");
        board.printBoard();
        char queenLocation = board.getPiece(0, 3).getType();
        assertEquals(queenLocation, 'q');

        //undo the promotion move
        board.undoLastMove();
        board.printBoard();
        char pawnLocation = board.getPiece(1, 3).getType();
        assertEquals(pawnLocation, 'p');

        System.out.println("testPawnBlackPromotion passed");
    }
}
