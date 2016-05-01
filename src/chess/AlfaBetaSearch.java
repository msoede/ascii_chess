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

    public int alphaBetaMax(int alpha, int beta, int depthleft, Board board) { // max == white
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
                if (depthleft == currentDepth) {
                    bestMove = childMove; //black best move
                }
                alpha = score;
            }
            if (score >= beta) {
                return beta;
            }
        }
        return alpha;
    }

    public int alphaBetaMin(int alpha, int beta, int depthleft, Board board) { // min == black
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
            int score = alphaBetaMax(alpha, beta, depthleft - 1, board);
            board.undoLastMove();
            if (score < beta) {
                if (depthleft == currentDepth) {
                    bestMove = childMove;
                }
                beta = score;
            }
            if (score <= alpha) {
                return alpha;
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
        board.setGameOver(false);
        board.setTimeOver(false);
        board.setStartTime();
        Move oldBestMove = null;
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

            int bestScore;
            if (board.isHumanPlayer() == true) { // human player == white
                bestScore = alphaBetaMin(-infinite, infinite, currentDepth, board);
            } else {
                bestScore = alphaBetaMax(-infinite, infinite, currentDepth, board);
            }

            String bedstMoveString = bestMove == null ? "NONE BEST MOVE " : bestMove.toString();
            bedstMoveString = board.isTimeOver() ? "Did not finish in time" : bedstMoveString;

            double now = (((double) (System.currentTimeMillis() - board.getStartTime())) / 1000); // get now time in millis
            System.out.format("| %5d | %8s sec |  %10d | %07d | %s\n", currentDepth, now, bestScore, nodes, bedstMoveString);
            if (board.isTimeOver()) {
                bestMove = oldBestMove;
                break;
            } else {
                oldBestMove = bestMove;
            }
        }
        System.out.println("+-------+--------------+-------------+---------+");
        System.out.println("best Move computer move:" + bestMove.getMoveString());
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
