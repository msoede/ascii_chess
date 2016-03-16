/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Objects.Board;
import chess.Objects.Fen;
import chess.Objects.Move;
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
public class EnPassantTest {

    Board board;
    Fen fen;
    MoveGen moveGen;

    public EnPassantTest() {
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
    public void testEnpassantWhitePawn() {
        System.out.println("testEnpassantWhitePawn");
        String fenString = "rnbqkbnr/8/8/8/1p1p1p1p/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Move move = new Move(1, 0, 3, 0, false, false, false, false, false, false,true, true);
        fen.loadFen(fenString, board);
        board.printBoard();
        //make sure the pieces are on the right spots 
        assertEquals('P', board.getPiece(1, 0).getType());
        assertEquals('P', board.getPiece(1, 1).getType());
        assertEquals('P', board.getPiece(1, 2).getType());
        assertEquals('P', board.getPiece(1, 3).getType());
        assertEquals('P', board.getPiece(1, 4).getType());
        assertEquals('P', board.getPiece(1, 5).getType());
        assertEquals('P', board.getPiece(1, 6).getType());
        assertEquals('P', board.getPiece(1, 7).getType());

        assertEquals('p', board.getPiece(3, 1).getType());
        assertEquals('p', board.getPiece(3, 3).getType());
        assertEquals('p', board.getPiece(3, 5).getType());
        assertEquals('p', board.getPiece(3, 7).getType());

        //make enpassant move
        board.makeMove(move);

        System.out.println("En passant square: " + board.getEnPassantString());

        System.out.println("after capture move");
        board.printBoard();
        //make sure the pawn has moved
        assertEquals('P', board.getPiece(3, 0).getType());
        assertEquals(null, board.getPiece(2, 0));
        assertEquals(null, board.getPiece(1, 0));
        assertEquals('p', board.getPiece(3, 1).getType());

        //make enPassant capture
        //???
        //???
        //???
        assertEquals(null, board.getPiece(3, 0).getType());
        assertEquals('p', board.getPiece(2, 0).getType());
        assertEquals(null, board.getPiece(1, 0));
        assertEquals(null, board.getPiece(3, 1));

        //undo the enpassant move
        board.undoLastMove();
        System.out.println("undo enpassant move");
        board.printBoard();
        assertEquals('P', board.getPiece(3, 0).getType());
        assertEquals(null, board.getPiece(2, 0));
        assertEquals(null, board.getPiece(1, 0));
        assertEquals('p', board.getPiece(3, 1).getType());

        //undo dobbelt move
        board.undoLastMove();
        System.out.println("undo pawn dobbelt move");
        board.printBoard();
        assertEquals(null, board.getPiece(3, 0).getType());
        assertEquals(null, board.getPiece(2, 0));
        assertEquals('P', board.getPiece(1, 0));
        assertEquals('p', board.getPiece(3, 1).getType());

        
        
        
        System.out.println("Pawn test done");
    }
}
