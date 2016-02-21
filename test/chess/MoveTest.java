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

import chess.Objects.Move;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

    public MoveTest() {
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
    public void testCompareMove() {
        System.out.println("compareMove");
        Move move1 = new Move(1, 1, 0, 0, true, true, true, true, true, false);
        Move move5 = new Move(1, 1, 0, 0, true, true, true, true, true, false);
        Move move2 = new Move(1, 1, 0, 0, true, true, true, true, true, true);
        Move move3 = new Move(0, 0, 1, 0, true, true, true, true, true, true);
        Move move4 = new Move(0, 0, 0, 2, false, true, true, true, true, true);

        assertEquals(true, move1.compareMove(move5));
        assertEquals(false, move1.compareMove(move2));
        assertEquals(false,move1.compareMove(move3));
        assertEquals(false,move1.compareMove(move4));
        assertEquals(true,move2.compareMove(move2));
    }
}
