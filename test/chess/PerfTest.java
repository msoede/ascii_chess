/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerfTest {

    private Board board;
    private Fen fen;
    private MoveGen moveGen;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        board = new Board("White", "Black");
        fen = new Fen();
        moveGen = new MoveGen();
    }

    @After
    public void tearDown() {
        board.clearBoard();
    }

    @Test
    public void testPerf1() {
        String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        int d[] = {20, 400, 8902, 197281, 4865609, 119060324};
        int r[] = {0, 0, 0, 0, 0, 0};
        fen.loadFen(fenString, board);

        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        r[0] = moveList.size();
        r[1] = r[0];
        for (Move childMove : moveList) {
            board.makeMove(childMove);
            ArrayList<Move> moveList1 = moveGen.generateAllMoves(board);
            board.undoLastMove();
            
            r[1] = r[1] + moveList1.size();
            System.out.println("size " +r[1]);
//            for (Move childMove1 : moveList1) {
//                board.makeMove(childMove1);
//            }
        }

        for (int i = 0; i < d.length; i++) {
            System.out.println("compare[" + i + "]: " + r[i] + "==" + d[i]);
            equals(r[i] == d[i]);
            assertEquals(r[i], d[i]);
        }
    }

    @Test
    public void testPerf2() {
        System.out.println("-> testPerf12()");
        assertEquals(false, true);
    }

    @Test
    public void testPerf3() {
        System.out.println("-> testPerf3()");
        assertEquals(false, true);
    }
}
