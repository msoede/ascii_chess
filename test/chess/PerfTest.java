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
    @Test
    public void testPerf1() {
        //rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ;D1 20 ;D2 400 ;D3 8902 ;D4 197281 ;D5 4865609 ;D6 119060324
        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 20, 1), true);
        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 400, 2), true);
        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 8902, 3), true);
        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 197281, 4), true);
        assertEquals(perf.makeTest("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 4865609, 5), true);
    }

    @Test
    public void testPerf2() {
        //r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1 ;D1 48 ;D2 2039 ;D3 97862 ;D4 4085603 ;D5 193690690 ;D6 8031647685
        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 48, 1), true);
        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 2039, 2), true);
        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 97862, 3), true);
        assertEquals(perf.makeTest("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq", 4085603, 4), true);
    }

    @Test
    public void testPerf3() {
        perf.setDebugMode(true);
        //4k3/8/8/8/8/8/8/4K2R w K - 0 1 ;D1 15 ;D2 66 ;D3 1197 ;D4 7059 ;D5 133987 ;D6 764643
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 15, 1), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 66, 2), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 1197, 3), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R w K - 0 1", 7059, 4), true);
    }

    @Test
    public void testPerf4() {
        //4k3/8/8/8/8/8/8/R3K3 w Q - 0 1 ;D1 16 ;D2 71 ;D3 1287 ;D4 7626 ;D5 145232 ;D6 846648
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 16, 1), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 71, 2), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 1287, 3), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1", 7626, 4), true);
    }

    @Test
    public void testPerf5() {
        //4k2r/8/8/8/8/8/8/4K3 w k - 0 1 ;D1 5 ;D2 75 ;D3 459 ;D4 8290 ;D5 47635 ;D6 899442
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 5, 1), true);
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 75, 2), true);
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 459, 3), true);
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 w k - 0 1", 8290, 4), true);
    }

    @Test
    public void testPerf6() {
        //r3k3/8/8/8/8/8/8/4K3 w q - 0 1 ;D1 5 ;D2 80 ;D3 493 ;D4 8897 ;D5 52710 ;D6 1001523
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 w q - 0 1", 5, 1), true);
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 w q - 0 1", 80, 2), true);
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 w q - 0 1", 493, 3), true);
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 w q - 0 1", 8897, 4), true);
    }

    @Test
    public void testPerf7() {
        //4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1 ;D1 26 ;D2 112 ;D3 3189 ;D4 17945 ;D5 532933 ;D6 2788982
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1", 26, 1), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1", 112, 2), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1", 3189, 3), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1", 17945, 4), true);
    }

    @Test
    public void testPerf8() {
        //r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1 ;D1 5 ;D2 130 ;D3 782 ;D4 22180 ;D5 118882 ;D6 3517770
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1", 5, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1", 130, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1", 782, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1", 22180, 4), true);
    }

    @Test
    public void testPerf9() {
        //8/8/8/8/8/8/6k1/4K2R w K - 0 1 ;D1 12 ;D2 38 ;D3 564 ;D4 2219 ;D5 37735 ;D6 185867
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R w K - 0 1", 12, 1), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R w K - 0 1", 38, 2), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R w K - 0 1", 564, 3), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R w K - 0 1", 2219, 4), true);
    }

    @Test
    public void testPerf10() {
        //8/8/8/8/8/8/1k6/R3K3 w Q - 0 1 ;D1 15 ;D2 65 ;D3 1018 ;D4 4573 ;D5 80619 ;D6 413018
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1", 15, 1), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1", 65, 2), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1", 1018, 3), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1", 4573, 4), true);
    }

    @Test
    public void testPerf11() {
        //4k2r/6K1/8/8/8/8/8/8 w k - 0 1 ;D1 3 ;D2 32 ;D3 134 ;D4 2073 ;D5 10485 ;D6 179869
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 w k - 0 1", 3, 1), true);
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 w k - 0 1", 32, 2), true);
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 w k - 0 1", 134, 3), true);
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 w k - 0 1", 2073, 4), true);
    }

    @Test
    public void testPerf12() {
        //r3k3/1K6/8/8/8/8/8/8 w q - 0 1 ;D1 4 ;D2 49 ;D3 243 ;D4 3991 ;D5 20780 ;D6 367724
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 w q - 0 1", 4, 1), true);
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 w q - 0 1", 49, 2), true);
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 w q - 0 1", 243, 3), true);
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 w q - 0 1", 3991, 4), true);
    }

    @Test
    public void testPerf13() {
        //r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1 ;D1 26 ;D2 568 ;D3 13744 ;D4 314346 ;D5 7594526 ;D6 179862938
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1", 26, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1", 568, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1", 13744, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1", 314346, 4), true);
    }

    @Test
    public void testPerf14() {
        //r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1 ;D1 25 ;D2 567 ;D3 14095 ;D4 328965 ;D5 8153719 ;D6 195629489
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1", 567, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1", 14095, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1", 328965, 4), true);
    }

    @Test
    public void testPerf15() {
        //r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1 ;D1 25 ;D2 548 ;D3 13502 ;D4 312835 ;D5 7736373 ;D6 184411439
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1", 548, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1", 13502, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1", 312835, 4), true);
    }

    @Test
    public void testPerf16() {
        //r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1 ;D1 25 ;D2 547 ;D3 13579 ;D4 316214 ;D5 7878456 ;D6 189224276
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1", 547, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1", 13579, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1", 316214, 4), true);
    }

    @Test
    public void testPerf17() {
        //1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1 ;D1 26 ;D2 583 ;D3 14252 ;D4 334705 ;D5 8198901 ;D6 198328929
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 26, 1), true);
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 583, 2), true);
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 14252, 3), true);
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 334705, 4), true);
    }

    @Test
    public void testPerf18() {
        //2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1 ;D1 25 ;D2 560 ;D3 13592 ;D4 317324 ;D5 7710115 ;D6 185959088
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 560, 2), true);
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 13592, 3), true);
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1", 317324, 4), true);
    }

    @Test
    public void testPerf19() {
        //r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1 ;D1 25 ;D2 560 ;D3 13607 ;D4 320792 ;D5 7848606 ;D6 190755813
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1", 560, 2), true);
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1", 13607, 3), true);
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1", 320792, 4), true);
    }

    @Test
    public void testPerf20() {
        //4k3/8/8/8/8/8/8/4K2R b K - 0 1 ;D1 5 ;D2 75 ;D3 459 ;D4 8290 ;D5 47635 ;D6 899442
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R b K - 0 1", 5, 1), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R b K - 0 1", 75, 2), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R b K - 0 1", 459, 3), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/4K2R b K - 0 1", 8290, 4), true);
    }

    @Test
    public void testPerf21() {
        //4k3/8/8/8/8/8/8/R3K3 b Q - 0 1 ;D1 5 ;D2 80 ;D3 493 ;D4 8897 ;D5 52710 ;D6 100152
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1", 5, 1), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1", 80, 2), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1", 493, 3), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1", 8897, 4), true);
    }

    @Test
    public void testPerf22() {
        //4k2r/8/8/8/8/8/8/4K3 b k - 0 1 ;D1 15 ;D2 66 ;D3 1197 ;D4 7059 ;D5 133987 ;D6 764643
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 b k - 0 1 ", 15, 1), true);
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 b k - 0 1 ", 66, 2), true);
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 b k - 0 1 ", 1197, 3), true);
        assertEquals(perf.makeTest("4k2r/8/8/8/8/8/8/4K3 b k - 0 1 ", 7059, 4), true);
    }

    @Test
    public void testPerf23() {
        //r3k3/8/8/8/8/8/8/4K3 b q - 0 1 ;D1 16 ;D2 71 ;D3 1287 ;D4 7626 ;D5 145232 ;D6 846648
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 b q - 0 1 ", 16, 1), true);
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 b q - 0 1 ", 71, 2), true);
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 b q - 0 1 ", 1287, 3), true);
        assertEquals(perf.makeTest("r3k3/8/8/8/8/8/8/4K3 b q - 0 1 ", 7626, 4), true);
    }

    @Test
    public void testPerf24() {
        //4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1 ;D1 5 ;D2 130 ;D3 782 ;D4 22180 ;D5 118882 ;D6 3517770
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1", 5, 1), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1",130, 2), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1",782, 3), true);
        assertEquals(perf.makeTest("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1",22180, 4), true);
    }

    @Test
    public void testPerf25() {
        //r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1 ;D1 26 ;D2 112 ;D3 3189 ;D4 17945 ;D5 532933 ;D6 2788982
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1", 26, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1",112, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1",3189, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1",17945, 4), true);
    }

    @Test
    public void testPerf26() {
        //8/8/8/8/8/8/6k1/4K2R b K - 0 1 ;D1 3 ;D2 32 ;D3 134 ;D4 2073 ;D5 10485 ;D6 179869
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R b K - 0 1", 3, 1), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R b K - 0 1",32, 2), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R b K - 0 1",134, 3), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/6k1/4K2R b K - 0 1",2073, 4), true);
    }

    @Test
    public void testPerf27() {
        //8/8/8/8/8/8/1k6/R3K3 b Q - 0 1 ;D1 4 ;D2 49 ;D3 243 ;D4 3991 ;D5 20780 ;D6 367724
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1 ", 4, 1), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1 ", 49, 2), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1 ", 243, 3), true);
        assertEquals(perf.makeTest("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1 ", 3991, 4), true);
    }

    @Test
    public void testPerf28() {
        //4k2r/6K1/8/8/8/8/8/8 b k - 0 1 ;D1 12 ;D2 38 ;D3 564 ;D4 2219 ;D5 37735 ;D6 185867
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 b k - 0 1", 12, 1), true);
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 b k - 0 1", 38, 2), true);
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 b k - 0 1", 564, 3), true);
        assertEquals(perf.makeTest("4k2r/6K1/8/8/8/8/8/8 b k - 0 1", 2219, 4), true);
    }

    @Test
    public void testPerf29() {
        //r3k3/1K6/8/8/8/8/8/8 b q - 0 1 ;D1 15 ;D2 65 ;D3 1018 ;D4 4573 ;D5 80619 ;D6 41301
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 b q - 0 1", 15, 1), true);
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 b q - 0 1", 65, 2), true);
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 b q - 0 1", 1018, 3), true);
        assertEquals(perf.makeTest("r3k3/1K6/8/8/8/8/8/8 b q - 0 1", 4573, 4), true);
    }

    @Test
    public void testPerf30() {
        //r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1 ;D1 26 ;D2 568 ;D3 13744 ;D4 314346 ;D5 7594526 ;D6 179862938
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1", 26, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1", 568, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1", 13744, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1", 314346, 4), true);
    }

    @Test
    public void testPerf31() {
        //r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1 ;D1 26 ;D2 583 ;D3 14252 ;D4 334705 ;D5 8198901 ;D6 198328929
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1", 26, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1", 583, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1", 14252, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1", 334705, 4), true);
    }

    @Test
    public void testPerf32() {
        //r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1 ;D1 25 ;D2 560 ;D3 13592 ;D4 317324 ;D5 7710115 ;D6 185959088
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1", 560, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1", 13592, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1", 317324, 4), true);
    }

    @Test
    public void testPerf33() {
        //r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1 ;D1 25 ;D2 560 ;D3 13607 ;D4 320792 ;D5 7848606 ;D6 190755813
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1", 560, 2), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1", 13607, 3), true);
        assertEquals(perf.makeTest("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1", 320792, 4), true);
    }

    @Test
    public void testPerf34() {
        //1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1 ;D1 25 ;D2 567 ;D3 14095 ;D4 328965 ;D5 8153719 ;D6 195629489
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 567, 2), true);
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 14095, 3), true);
        assertEquals(perf.makeTest("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 328965, 4), true);
    }

    @Test
    public void testPerf35() {
        //2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1 ;D1 25 ;D2 548 ;D3 13502 ;D4 312835 ;D5 7736373 ;D6 184411439
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 548, 2), true);
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 13502, 3), true);
        assertEquals(perf.makeTest("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1", 312835, 4), true);
    }

    @Test
    public void testPerf36() {
        //r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1 ;D1 25 ;D2 547 ;D3 13579 ;D4 316214 ;D5 7878456 ;D6 189224276
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1", 25, 1), true);
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1", 547, 2), true);
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1", 13579, 3), true);
        assertEquals(perf.makeTest("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1", 316214, 4), true);
    }

    @Test
    public void testPerf37() {
        //8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1 ;D1 14 ;D2 195 ;D3 2760 ;D4 38675 ;D5 570726 ;D6 8107539
        assertEquals(perf.makeTest("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1", 14, 1), true);
        assertEquals(perf.makeTest("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1", 195, 2), true);
        assertEquals(perf.makeTest("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1", 2760, 3), true);
        assertEquals(perf.makeTest("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1", 38675, 4), true);
    }

    @Test
    public void testPerf38() {
        //8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1 ;D1 11 ;D2 156 ;D3 1636 ;D4 20534 ;D5 223507 ;D6 2594412
        assertEquals(perf.makeTest("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1", 11, 1), true);
        assertEquals(perf.makeTest("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1", 156, 2), true);
        assertEquals(perf.makeTest("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1", 1636, 3), true);
        assertEquals(perf.makeTest("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1", 20534, 4), true);
    }

    @Test
    public void testPerf39() {
        //8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1 ;D1 19 ;D2 289 ;D3 4442 ;D4 73584 ;D5 1198299 ;D6 19870403
        assertEquals(perf.makeTest("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1", 19, 1), true);
        assertEquals(perf.makeTest("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1", 289, 2), true);
        assertEquals(perf.makeTest("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1", 4442, 3), true);
        assertEquals(perf.makeTest("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1", 73584, 4), true);
    }
}
