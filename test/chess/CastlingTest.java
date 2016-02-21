//                               _      
//                              | |     
//  _ __ ___  ___  ___   ___  __| | ___ 
// | '_ ` _ \/ __|/ _ \ / _ \/ _` |/ _ \
// | | | | | \__ \ (_) |  __/ (_| |  __/
// |_|_|_| |_|___/\___/ \___|\__,_|\___|
//  / ____| |                           
// | |    | |__   ___  ___ ___          
// | |    | '_ \ / _ \/ __/ __|         
// | |____| | | |  __/\__ \__ \         
//  \_____|_| |_|\___||___/___/         
// |__   __|      | | (_)               
//    | | ___  ___| |_ _ _ __   __ _    
//    | |/ _ \/ __| __| | '_ \ / _` |   
//    | |  __/\__ \ |_| | | | | (_| |   
//    |_|\___||___/\__|_|_| |_|\__, |   
//                              __/ |   
//                             |___/    
//
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
public class CastlingTest {

    Board board;
    Fen fen;
    MoveGen moveGen;

    public CastlingTest() {

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
    public void testCastlingWhiteKing() {
        System.out.println("testCastlingWhiteKing start");
        String fenString = "4k2r/8/8/8/8/8/8/4K2R w KQkq - 0 1";
        int d = 15; //exp resualt
        int r; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();
        assertEquals(d, r);

        Move castlingMove = null;
        for (Move childMove : moveList) {
            if (childMove.isCastleWhiteKing()) {
                castlingMove = childMove;
                System.out.println("Castling move found: " + castlingMove.toString());
                break;
            }
        }

        System.out.println("before castling");
        board.printBoard();
        board.makeMove(castlingMove);
        System.out.println("After castling");
        board.printBoard();
        char kingLocation = board.getPiece(0, 6).getType();
        char rookLocation = board.getPiece(0, 5).getType();
        assertEquals(kingLocation, 'K');
        assertEquals(rookLocation, 'R');

        board.undoLastMove();
        System.out.println("undo castling");
        board.printBoard();
        kingLocation = board.getPiece(0, 4).getType();
        rookLocation = board.getPiece(0, 7).getType();
        assertEquals(kingLocation, 'K');
        assertEquals(rookLocation, 'R');

        System.out.println("testCastlingWhiteKing passed");
    }

    @Test
    public void testCastlingBlackKing() {
        System.out.println("testCastlingBlackKing start");
        String fenString = "4k2r/8/8/8/8/8/8/4K2R b - - 0 1";
        int d = 15; //exp resualt
        int r = 0; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();
        assertEquals(d, r);

        Move castlingMove = null;
        for (Move childMove : moveList) {
            if (childMove.isCastleBlackKing()) {
                castlingMove = childMove;
                break;
            }
        }

        System.out.println("before castling");
        board.printBoard();
        board.makeMove(castlingMove);
        System.out.println("After castling");
        board.printBoard();
        char kingLocation = board.getPiece(7, 6).getType();
        char rookLocation = board.getPiece(7, 5).getType();
        assertEquals(kingLocation, 'k');
        assertEquals(rookLocation, 'r');

        board.undoLastMove();
        System.out.println("undo castling");
        board.printBoard();
        kingLocation = board.getPiece(7, 4).getType();
        rookLocation = board.getPiece(7, 7).getType();
        assertEquals(kingLocation, 'k');
        assertEquals(rookLocation, 'r');
        System.out.println("testCastlingBlackKing passed");
    }

    @Test
    public void testCastlingWhiteQueen() {
        System.out.println("testCastlingWhiteQueen start");
        String fenString = "r3k3/8/8/8/8/8/8/R3K3 w - - 0 1";
        int d = 16; //exp resualt
        int r = 0; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();
        assertEquals(d, r);

        Move castlingMove = null;
        for (Move childMove : moveList) {
            if (childMove.isCastleWhiteQueen()) {
                castlingMove = childMove;
                break;
            }
        }
        System.out.println("before castling");
        board.printBoard();
        board.makeMove(castlingMove);
        System.out.println("After castling");
        board.printBoard();
        char kingLocation = board.getPiece(0, 2).getType();
        char rookLocation = board.getPiece(0, 3).getType();
        assertEquals(kingLocation, 'K');
        assertEquals(rookLocation, 'R');

        board.undoLastMove();
        System.out.println("undo castling");
        board.printBoard();
        kingLocation = board.getPiece(0, 4).getType();
        rookLocation = board.getPiece(0, 0).getType();
        assertEquals(kingLocation, 'K');
        assertEquals(rookLocation, 'R');

        System.out.println("testCastlingBlackKing passed");
    }

    @Test
    public void testCastlingBlackQueen() {
        System.out.println("testCastlingBlackQueen start");
        String fenString = "r3k3/8/8/8/8/8/8/R3K3 b - - 0 1";
        int d = 16; //exp resualt
        int r = 0; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();
        assertEquals(d, r);

        Move castlingMove = null;
        for (Move childMove : moveList) {
            if (childMove.isCastleBlackQueen()) {
                castlingMove = childMove;
                break;
            }
        }
        System.out.println("before castling");
        board.printBoard();
        board.makeMove(castlingMove);
        System.out.println("after castling");
        board.printBoard();
        char kingLocation = board.getPiece(7, 2).getType();
        char rookLocation = board.getPiece(7, 3).getType();
        assertEquals(kingLocation, 'k');
        assertEquals(rookLocation, 'r');

        board.undoLastMove();
        System.out.println("undo castling");
        board.printBoard();

        kingLocation = board.getPiece(7, 4).getType();
        rookLocation = board.getPiece(7, 0).getType();
        assertEquals(kingLocation, 'k');
        assertEquals(rookLocation, 'r');

        System.out.println("testCastlingBlackKing passed");
    }
}
