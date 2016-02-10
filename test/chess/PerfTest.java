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

//    @Test
//    public void testPerf1() {
//        String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
//        int d[] = {20, 400, 8902, 197281, 4865609, 119060324};
//        int r[] = {0, 0, 0, 0, 0, 0};
//        fen.loadFen(fenString, board);
//
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r[0] = moveList.size();
//        r[1] = r[0];
//        for (Move childMove : moveList) {
//            board.makeMove(childMove);
//            ArrayList<Move> moveList1 = moveGen.generateAllMoves(board);
//            board.undoLastMove();
//
//            r[1] = r[1] + moveList1.size();
////            System.out.println("size " + r[1]);
////            for (Move childMove1 : moveList1) {
////                board.makeMove(childMove1);
////            }
//        }
//
//        for (int i = 0; i < d.length; i++) {
//            System.out.println("compare[" + i + "]: " + r[i] + "==" + d[i]);
//            assertEquals(d[i], r[i]);
//        }
//    }
    @Test
    public void testPerf2() {
//        String fenString = "8/8/8/8/8/8/6k1/4K2R w K - 0 1";
        String fenString = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq";
//        String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        int d[] = {48, 2039, 97862, 4085603, 193690690};
        int r[] = {0, 0, 0, 0, 0};
        fen.loadFen(fenString, board);

        int seachDepth = 1;
        int movesTotal = moveGen.generateAllForDepth(board, seachDepth);
        System.out.println("Number of moves done at depth " + seachDepth + ": " + movesTotal);
        assertEquals(2039, movesTotal);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//
//        r[0] = moveList.size();
//        r[1] = r[0];
//        System.out.println("pert testing");
//        int j = 0;
//        for (Move childMove : moveList) {
//            System.out.println(j + " : " + childMove.toString());
//            j++;
//            board.makeMove(childMove);
//            board.printBoard();
//            board.undoLastMove();
//        }
//
//        for (int i = 0; i < d.length; i++) {
//            System.out.println("compare[" + i + "]: " + r[i] + "==" + d[i]);
//            equals(r[i] == d[i]);
//            assertEquals(d[i], r[i]);
//        }
    }
//    @Test
//    public void testPerf3() {
////        System.out.println("-> testPerf3()");
////        String fenString = "4k3/8/8/8/8/8/8/4K2R w K - 0 1";
////        int d[] = {15, 2039, 97862, 4085603, 193690690};
////        int r[] = {0, 0, 0, 0, 0};
////        fen.loadFen(fenString, board);
////
////        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
////
////        r[0] = moveList.size();
////        r[1] = r[0];
////        System.out.println("pert testing");
////        int j = 0;
////        for (Move childMove : moveList) {
////            System.out.println(j + " : " + childMove.toString());
////            j++;
////            board.makeMove(childMove);
////            board.printBoard();
////            board.undoLastMove();
////        }
////
////        for (int i = 0; i < d.length; i++) {
////            System.out.println("compare[" + i + "]: " + r[i] + "==" + d[i]);
////            equals(r[i] == d[i]);
////            assertEquals(r[i], d[i]);
////        }
//    }
//    @Test
//    public void testWhitePawnPromotion() {
//        String fenString = "8/3P2p1/8/8/8/8/8/8 w - - 0 1";
//        int d = 1; //exp resualt
//        int r = 0; // test resualt
//        fen.loadFen(fenString, board);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r = moveList.size();
//        assertEquals(d, r);
//        board.makeMove(moveList.get(0));
//        board.printBoard();
//        char queenLocation = board.getPiece(7, 3).getType();
//        assertEquals(queenLocation, 'Q');
//        System.out.println("testPawnWhitePromotion passed");
//    }
//
//    @Test
//    public void testBlackPawnPromotion() {
//        String fenString = "8/8/8/8/8/8/3p2P1/8 b - - 0 1";
//        int d = 1; //exp resualt
//        int r = 0; // test resualt
//        fen.loadFen(fenString, board);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r = moveList.size();
//
//        assertEquals(d, r);
//        board.makeMove(moveList.get(0));
//        board.printBoard();
//        char queenLocation = board.getPiece(0, 3).getType();
//        assertEquals(queenLocation, 'q');
//        System.out.println("testPawnBlackPromotion passed");
//    }
//
//    @Test
//    public void testCastlingWhiteKing() {
//        String fenString = "4k2r/8/8/8/8/8/8/4K2R w - - 0 1";
//        int d = 15; //exp resualt
//        int r = 0; // test resualt
//        fen.loadFen(fenString, board);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r = moveList.size();
//        assertEquals(d, r);
//
//        Move castlingMove = null;
//        for (Move childMove : moveList) {
//            if (childMove.isCastleWhiteKing()) {
//                castlingMove = childMove;
//                break;
//            }
//        }
//
//        board.makeMove(castlingMove);
//        board.printBoard();
//        char kingLocation = board.getPiece(0, 6).getType();
//        char rookLocation = board.getPiece(0, 5).getType();
//        assertEquals(kingLocation, 'K');
//        assertEquals(rookLocation, 'R');
//        System.out.println("testCastlingWhiteKing passed");
//    }
//
//    @Test
//    public void testCastlingBlackKing() {
//        String fenString = "4k2r/8/8/8/8/8/8/4K2R b - - 0 1";
//        int d = 15; //exp resualt
//        int r = 0; // test resualt
//        fen.loadFen(fenString, board);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r = moveList.size();
//        assertEquals(d, r);
//
//        Move castlingMove = null;
//        for (Move childMove : moveList) {
//            if (childMove.isCastleBlackKing()) {
//                castlingMove = childMove;
//                break;
//            }
//        }
//
//        board.makeMove(castlingMove);
//        board.printBoard();
//        char kingLocation = board.getPiece(7, 6).getType();
//        char rookLocation = board.getPiece(7, 5).getType();
//        assertEquals(kingLocation, 'k');
//        assertEquals(rookLocation, 'r');
//        System.out.println("testCastlingBlackKing passed");
//    }
//
//    @Test
//    public void testCastlingWhiteQueen() {
//        String fenString = "r3k3/8/8/8/8/8/8/R3K3 w - - 0 1";
//        int d = 16; //exp resualt
//        int r = 0; // test resualt
//        fen.loadFen(fenString, board);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r = moveList.size();
//        assertEquals(d, r);
//
//        Move castlingMove = null;
//        for (Move childMove : moveList) {
//            if (childMove.isCastleWhiteQueen()) {
//                castlingMove = childMove;
//                break;
//            }
//        }
//
//        board.makeMove(castlingMove);
//        board.printBoard();
//        char kingLocation = board.getPiece(0, 2).getType();
//        char rookLocation = board.getPiece(0, 3).getType();
//        assertEquals(kingLocation, 'K');
//        assertEquals(rookLocation, 'R');
//        System.out.println("testCastlingBlackKing passed");
//    }
//
//    @Test
//    public void testCastlingBlackQueen() {
//        String fenString = "r3k3/8/8/8/8/8/8/R3K3 b - - 0 1";
//        int d = 16; //exp resualt
//        int r = 0; // test resualt
//        fen.loadFen(fenString, board);
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        r = moveList.size();
//        assertEquals(d, r);
//
//        Move castlingMove = null;
//        for (Move childMove : moveList) {
//            if (childMove.isCastleBlackQueen()) {
//                castlingMove = childMove;
//                break;
//            }
//        }
//
//        board.makeMove(castlingMove);
//        board.printBoard();
//        char kingLocation = board.getPiece(7, 2).getType();
//        char rookLocation = board.getPiece(7, 3).getType();
//        assertEquals(kingLocation, 'k');
//        assertEquals(rookLocation, 'r');
//        System.out.println("testCastlingBlackKing passed");
//    }
}
