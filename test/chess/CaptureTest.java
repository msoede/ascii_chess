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
        String fenString = "8/3P2p1/8/8/8/8/8/8 w - - 0 1";
        int d = 1; //exp resualt
        int r = 0; // test resualt
        fen.loadFen(fenString, board);
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r = moveList.size();
        assertEquals(d, r);
        System.out.println("Board before promotion move to make: " + moveList.get(0).toString());
        board.printBoard();
        board.makeMove(moveList.get(0));
        System.out.println("Board after promotion");
        board.printBoard();
        char queenLocation = board.getPiece(7, 3).getType();
        assertEquals(queenLocation, 'Q');
        System.out.println("testPawnWhitePromotion passed");

        //undo the promotion move
        board.undoLastMove();
        board.printBoard();
        char pawnLocation = board.getPiece(6, 3).getType();
        assertEquals(pawnLocation, 'p');
    }
}
