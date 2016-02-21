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
import chess.Objects.Move;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test
    public void testUndoLastMove() {
        System.out.println("undoLastMove");
        Board board = new Board();
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
