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
import chess.Objects.Move;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mikkel Soede
 * @version 1.0
 * @description
 * @date
 */
public class AlfaBetaSearch {

    private final MoveGen moveGen;
    private final Evaluation evaluation;
    private final int infinite = Integer.MAX_VALUE - 1000;
    private int nodes = 0;
    private Move bestMove;

    public AlfaBetaSearch(Evaluation evaluation) {
        this.moveGen = new MoveGen();
        this.evaluation = evaluation;
    }

    public void clearForSearch() {
        this.nodes = 0;
        this.bestMove = null;
    }

    public int alphaBetaMax(int alpha, int beta, int depthleft, Board board) {

        if ((nodes % 1024) == 0) {
            checkForTimeIsUp(board);
        }
        if (board.isGameOver()) {
            return 0;
        }

        if (depthleft == 0) {
            nodes++;
            return evaluation.evaluateBoard(board);
        }
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);

        for (Move childMove : moveList) {
            board.makeMove(childMove);
            int score = alphaBetaMin(alpha, beta, depthleft - 1, board);
            board.undoLastMove();
            if (score > alpha) {
                if (board.isHumanPlayer() == true) {
                    bestMove = childMove; //black best move
                }
                alpha = score; // alpha acts like max in MiniMax
            }
            if (score >= beta) {
                return beta;   // fail hard beta-cutoff
            }
        }
        return alpha;
    }

    public int alphaBetaMin(int alpha, int beta, int depthleft, Board board) {

        if ((nodes % 1024) == 0) {
            checkForTimeIsUp(board);
        }
        if (board.isGameOver()) {
            return 0;
        }

        if (depthleft == 0) {
            nodes++;
            return evaluation.evaluateBoard(board);
        }
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        for (Move childMove : moveList) {
            board.makeMove(childMove);
            int score = alphaBetaMax(alpha, beta, depthleft - 1, board);
            board.undoLastMove();
            if (score < beta) {
                if (board.isHumanPlayer() == false) {
                    bestMove = childMove; //white best move
                }
                beta = score; // beta acts like min in MiniMax
            }
            if (score <= alpha) {
                return alpha; // fail hard alpha-cutoff
            }
        }
        return beta;
    }

    /**
     * iterative deepening
     *
     * @param board
     */
    public void FindBedstMove(Board board) {
        long startTime = System.currentTimeMillis();
        int maxDepth = board.getSearchDepth();
        int currentDepth;
        board.setGameOver(false);
        board.setStartTime();

        System.out.println("Iterative deepening max depth:" + board.getSearchDepth() + " search time: " + board.getSearchTime());
        System.out.println("+-------+--------------+-------+---------+");
        System.out.println("| depth |     time     | score |  nodes  |");
        System.out.println("+-------+--------------+-------+---------+");
        for (currentDepth = 1; currentDepth < maxDepth; currentDepth++) {
            if (board.isGameOver()) {
                break;
            }
            else{
                clearForSearch();
            }
            int bestScore = alphaBetaMax(-infinite, infinite, currentDepth, board);

            String bedstMoveString = bestMove == null ? "NONE BEST MOVE " : bestMove.toString();

            //check for time is up
            double now = (((double) (System.currentTimeMillis() - startTime)) / 1000);
            System.out.format("| %5d | %8s sec |  %4d | %07d | %s\n", currentDepth, now, bestScore, nodes, bedstMoveString);
        }
        System.out.println("+-------+--------------+-------+---------+");
        System.out.println("best Move:" + bestMove.toString());
        board.makeMove(bestMove);

    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int min(int a, int b) {
        return a < b ? a : b;
    }

    private void checkForTimeIsUp(Board board) {
        if (board.getEndTime() <= System.currentTimeMillis()) {
            System.out.println("Time is up!!");
            board.setGameOver(true);
        }
    }
}
