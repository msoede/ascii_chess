//                                       _      
//                                      | |     
//          _ __ ___  ___  ___   ___  __| | ___ 
//         | '_ ` _ \/ __|/ _ \ / _ \/ _` |/ _ \
//         | | | | | \__ \ (_) |  __/ (_| |  __/
//         |_|_|_| |_|___/\___/ \___|\__,_|\___|
//          / ____| |                           
//         | |    | |__   ___  ___ ___          
//         | |    | '_ \ / _ \/ __/ __|         
//         | |____| | | |  __/\__ \__ \         
//          \_____|_| |_|\___||___/___/   
//
package chess;

import chess.Objects.Fen;
import chess.Objects.Board;
import chess.Objects.Move;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerfTest {

    Perf perf;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        perf = new Perf(true);
    }

    @After
    public void tearDown() {
        perf.clearBoard();
    }

    @Test
    public void testPerf1() {
        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 20, 1), true);
    }

    @Test
    public void testPerf2() {
        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 48, 1), true);
    }

    @Test
    public void testPerf3() {
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 15, 1), true);
    }

    @Test
    public void testPerf4() {
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 16, 1), true);
    }

    @Test
    public void testPerf5() {
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 5, 1), true);
    }

    @Test
    public void testPerf6() {
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 w q - 0 1", 5, 1), true);
    }

    @Test
    public void testPerf7() {
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1", 26, 1), true);
    }

    @Test
    public void testPerf8() {
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1", 5, 1), true);
    }

    @Test
    public void testPerf9() {
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R w K - 0 1", 12, 1), true);
    }

    @Test
    public void testPerf10() {
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1", 15, 1), true);
    }

    @Test
    public void testPerf11() {
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 w k - 0 1", 3, 1), true);
    }

    @Test
    public void testPerf12() {
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 w q - 0 1", 4, 1), true);
    }

    @Test
    public void testPerf13() {
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1", 26, 1), true);
    }

    @Test
    public void testPerf14() {
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1", 25, 1), true);
    }

    @Test
    public void testPerf15() {
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1", 25, 1), true);
    }
//    @Test
//    public void testPerf3() {
//        System.out.println("-> testPerf3()");
//        String fenString = "4k3/8/8/8/8/8/8/4K2R w K - 0 1";
//        int d[] = {15, 2039, 97862, 4085603, 193690690};
//        int r[] = {0, 0, 0, 0, 0};
//        fen.loadFen(fenString, board);
//
//        r[0] = moveGen.generateAllForDepth(board, 1);
//        board.clearBoard();
//        fen.loadFen(fenString, board);
//        r[1] = moveGen.generateAllForDepth(board, 2);
//
//        for (int i = 0; i < d.length; i++) {
//            System.out.println("compare[" + i + "]: " + r[i] + "==" + d[i]);
//            assertEquals(d[i], r[i]);
//        }
//    }
}
