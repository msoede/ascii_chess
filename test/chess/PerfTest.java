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
        perf = new Perf(false);
    }

    @After
    public void tearDown() {
        perf.clearBoard();
    }

//    @Test
//    public void testPerf1() {
//        //rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ;D1 20 ;D2 400 ;D3 8902 ;D4 197281 ;D5 4865609 ;D6 119060324
//        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 20, 1), true);
//        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 400, 2), true);
//        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 8902, 3), true);
//        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 197281, 4), true);
//        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 4865609, 5), true);
//    }
    @Test
    public void testPerf2() {
        //r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1 ;D1 48 ;D2 2039 ;D3 97862 ;D4 4085603 ;D5 193690690 ;D6 8031647685
//        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 48, 1), true);
        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 2039, 2), true);
//        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 97862, 3), true);
//        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 4085603, 4), true);
    }
//
//    @Test
//    public void testPerf3() {
//        perf.setDebugMode(true);
//        //4k3/8/8/8/8/8/8/4K2R w K - 0 1 ;D1 15 ;D2 66 ;D3 1197 ;D4 7059 ;D5 133987 ;D6 764643
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 15, 1), true);
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 66, 2), true);
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 1197, 3), true);
////        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 7059, 4), true);
//    }
//
//    @Test
//    public void testPerf4() {
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 16, 1), true);
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 71, 2), true);
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 1287, 3), true);
////        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 7626, 4), true);
//    }
//
//    @Test
//    public void testPerf5() {
//        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 5, 1), true);
//        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 75, 2), true);
//        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 459, 3), true);
//        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 8290, 4), true);
//    }
//
//    @Test
//    public void testPerf6() {
//        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 w q - 0 1", 5, 1), true);
//    }
//
//    @Test
//    public void testPerf7() {
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1", 26, 1), true);
//    }
//
//    @Test
//    public void testPerf8() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1", 5, 1), true);
//    }
//
//    @Test
//    public void testPerf9() {
//        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R w K - 0 1", 12, 1), true);
//    }
//
//    @Test
//    public void testPerf10() {
//        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1", 15, 1), true);
//    }
//
//    @Test
//    public void testPerf11() {
//        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 w k - 0 1", 3, 1), true);
//    }
//
//    @Test
//    public void testPerf12() {
//        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 w q - 0 1", 4, 1), true);
//    }
//
//    @Test
//    public void testPerf13() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1", 26, 1), true);
//    }
//
//    @Test
//    public void testPerf14() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf15() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf16() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf17() {
//        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 26, 1), true);
//    }
//
//    @Test
//    public void testPerf18() {
//        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf19() {
//        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1 ", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf20() {
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R b K - 0 1 ", 5, 1), true);
//    }
//
//    @Test
//    public void testPerf21() {
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1", 5, 1), true);
//    }
//
//    @Test
//    public void testPerf22() {
//        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 b k - 0 1 ", 15, 1), true);
//    }
//
//    @Test
//    public void testPerf23() {
//        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 b q - 0 1 ", 16, 1), true);
//    }
//
//    @Test
//    public void testPerf24() {
//        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1", 5, 1), true);
//    }
//
//    @Test
//    public void testPerf25() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1", 26, 1), true);
//    }
//
//    @Test
//    public void testPerf26() {
//        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R b K - 0 1", 3, 1), true);
//    }
//
//    @Test
//    public void testPerf27() {
//        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1 ", 4, 1), true);
//    }
//
//    @Test
//    public void testPerf28() {
//        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 b k - 0 1", 12, 1), true);
//    }
//
//    @Test
//    public void testPerf29() {
//        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 b q - 0 1", 15, 1), true);
//    }
//
//    @Test
//    public void testPerf30() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1", 26, 1), true);
//    }
//
//    @Test
//    public void testPerf31() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1", 26, 1), true);
//    }
//
//    @Test
//    public void testPerf32() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf33() {
//        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf34() {
//        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf35() {
//        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf36() {
//        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1", 25, 1), true);
//    }
//
//    @Test
//    public void testPerf37() {
//        assertEquals(perf.makeTest("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1", 14, 1), true);
//    }
//
//    @Test
//    public void testPerf38() {
//        assertEquals(perf.makeTest("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1", 11, 1), true);
//    }
//
//    @Test
//    public void testPerf39() {
//        assertEquals(perf.makeTest("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1", 19, 1), true);
//    }
}
