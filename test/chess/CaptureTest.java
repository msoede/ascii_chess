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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaptureTest {

    Board board;
    Fen fen;
    MoveGen moveGen;

    public CaptureTest() {
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
    public void testCapureMove() {
        String fenString = "8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1 ";
        Move attackMove = new Move(2, 5, 1, 6, false, false, false, false, false, false);
        attackMove.setCaputreMove(board.getPiece(6, 1).getName());
        fen.loadFen(fenString, board);
        System.out.println("before capture move");
        board.printBoard();
        assertEquals('K', board.getPiece(2, 5).getType());
        assertEquals('n', board.getPiece(1, 6).getType());
        //make capture move
        board.makeMove(attackMove);
        System.out.println("after capture move");
        board.printBoard();
        assertEquals(null, board.getPiece(2, 5));
        assertEquals('K', board.getPiece(1, 6).getType());

        //undo the capture move
        board.undoLastMove();
        System.out.println("undo capture move");
        board.printBoard();
        assertEquals('K', board.getPiece(2, 5).getType());
        assertEquals('n', board.getPiece(1, 6).getType());
    }
}
