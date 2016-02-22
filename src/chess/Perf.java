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

import chess.Objects.Board;
import chess.Objects.Fen;
import chess.Objects.Move;
import java.util.ArrayList;

/**
 * @author Mikkel Soede
 * @version 1.0
 * @description
 * @date
 */
public class Perf {

    private boolean debugMode;
    private final Board board;
    private final Fen fen;
    private final MoveGen moveGen;
    private int total = 0;

    public Perf(boolean debugMode) {
        this.debugMode = debugMode;
        board = new Board();
        fen = new Fen();
        moveGen = new MoveGen();
    }

    public void setDebugMode(boolean input) {
        this.debugMode = input;
    }

    public void clearBoard() {
        board.clearBoard();
    }

    public int generateAllForDepth(Board board, int depth) {
        perfTest(depth, board);
        return total;
    }

    private void perf1(int depth, Board board) {
        if (depth <= 0) {
            total++;
            return;
        }

        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        for (Move childMove : moveList) {
//            System.out.println("move(" + total + "," + depth + ") " + childMove.toString());
            board.makeMove(childMove);
            //board.printBoard();
            perf1(depth - 1, board);
            board.undoLastMove();
        }
    }

    private void perfTest(int depth, Board board) {
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        int i = 0;
        for (Move childMove : moveList) {
//            System.out.println(total + " : " + childMove.toString());
            board.makeMove(childMove);
            perf1(depth - 1, board);
            board.undoLastMove();
//            if (debugMode) {
//                System.out.println("move[" + i + "]: " + childMove.toString());
//                i++;
//            }
        }
    }

    public boolean makeTest(String fenString, int expRes, int seachDepth) {
        total = 0;
        fen.loadFen(fenString, board);
        int movesTotal = generateAllForDepth(board, seachDepth);

        boolean resualt = movesTotal == expRes;
        if (!resualt) {
            board.printBoard();
            System.out.println("Failed fen:   " + fenString);
            System.out.println("Failed depth: " + seachDepth);
            System.out.println("Failed expected: " + expRes + " but was " + movesTotal);
        }
        return movesTotal == expRes;
    }
}
