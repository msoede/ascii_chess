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
    private int currentDepth;

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
        if (board.isGameOver() || board.isTimeOver()) {
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
                if (board.isHumanPlayer() == true && depthleft == currentDepth) {
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
        if (board.isGameOver() || board.isTimeOver()) {
            return 0;
        }

        if (depthleft == 0) {
            nodes++;
            return -evaluation.evaluateBoard(board);
        }
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        for (Move childMove : moveList) {
            board.makeMove(childMove);
            int score = alphaBetaMax(alpha, beta, depthleft - 1, board);
            board.undoLastMove();
            if (score < beta) {
                if (board.isHumanPlayer() == false && depthleft == currentDepth) {
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
        int maxDepth = board.getSearchDepth();
        board.setSearchTime(5);
        board.setGameOver(false);
        board.setTimeOver(false);
        board.setStartTime();

        System.out.println("Iterative deepening max depth:" + board.getSearchDepth() + " search time: " + board.getSearchTime());
        System.out.println("+-------+--------------+-------------+---------+");
        System.out.println("| depth |     time     |    score    |  nodes  | movestring");
        System.out.println("+-------+--------------+-------------+---------+");
        for (currentDepth = 1; currentDepth < maxDepth; currentDepth++) {
            if (board.isGameOver()) {
                System.out.println("+----------------------------------------------+");
                System.out.println("|              !!GAME IS OVER!!                |");
                System.out.println("+----------------------------------------------+");
                break;
            } else {
                clearForSearch();
            }
            int bestScore = alphaBetaMax(-infinite, infinite, currentDepth, board);

            String bedstMoveString = bestMove == null ? "NONE BEST MOVE " : bestMove.toString();

            double now = (((double) (System.currentTimeMillis() - board.getStartTime())) / 1000); // get now time in millis
            System.out.format("| %5d | %8s sec |  %10d | %07d | %s\n", currentDepth, now, bestScore, nodes, bedstMoveString);
            if (board.isTimeOver()) {
                break;
            }
        }
        System.out.println("+-------+--------------+-------------+---------+");
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

        if (board.getEndTime() <= System.currentTimeMillis() && !board.isTimeOver()) {
            System.out.println("+----------------------------------------------+");
            System.out.println("|                Time is up                    |");
            System.out.println("+----------------------------------------------+");
            board.setTimeOver(true);
        }
    }
}
