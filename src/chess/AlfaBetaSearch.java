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

    public AlfaBetaSearch() {
        this.moveGen = new MoveGen();
        this.evaluation = new Evaluation();
    }

    int alphaBetaMax(int alpha, int beta, int depthleft, Board board) {

        if (depthleft == 0) {
            return evaluation.evaluateBoard(board);
        }
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);

        for (Move childMove : moveList) {
            board.makeMove(childMove);
            board.printBoard();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlfaBetaSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
            int score = alphaBetaMin(alpha, beta, depthleft - 1, board);
            board.undoLastMove();
            if (score >= beta) {
                return beta;   // fail hard beta-cutoff
            }
            if (score > alpha) {
                alpha = score; // alpha acts like max in MiniMax
            }
        }
        return alpha;
    }

    int alphaBetaMin(int alpha, int beta, int depthleft, Board board) {
        if (depthleft == 0) {
            return evaluation.evaluateBoard(board);
        }
        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
        for (Move childMove : moveList) {
            board.makeMove(childMove);
            int score = alphaBetaMax(alpha, beta, depthleft - 1, board);
            board.undoLastMove();
            if (score <= alpha) {
                return alpha; // fail hard alpha-cutoff
            }
            if (score < beta) {
                beta = score; // beta acts like min in MiniMax
            }
        }
        return beta;
    }

    //    int alphaBeta( int alpha, int beta, int depthleft ) {
    //    int bestscore = -oo;
    //    if( depthleft == 0 ) return quiesce( alpha, beta );
    //    for ( all moves)  {
    //       score = -alphaBeta( -beta, -alpha, depthleft - 1 );
    //       if( score >= beta )
    //          return score;  // fail-soft beta-cutoff
    //       if( score > bestscore ) {
    //          bestscore = score;
    //          if( score > alpha )
    //             alpha = score;
    //       }
    //    }
    //    return bestscore;
    //}
//    public int aplfaBetaTest(int alpha, int beta, int depth, Board board) {
//        int bestscore = -infinite;
//        if (depth == 0) {
//            return evaluation.evaluateBoard(board);
//        }
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        for (Move childMove : moveList) {
//            board.makeMove(childMove);
////            board.switchSide();
//            int score = -aplfaBetaTest(-beta, -alpha, depth - 1, board);
////            board.switchSide();
//            board.undoLastMove();
//            if (score >= beta) {
//                return score;  // fail-soft beta-cutoff
//            }
//            if (score > bestscore) {
//                bestscore = score;
//                if (score > alpha) {
//                    alpha = score;
//                }
//            }
//        }
//        return bestscore;
//    }
//    // source: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
//    //
//    //01 function alphabeta(node, depth, α, β, maximizingPlayer)
//    //02      if depth = 0 or node is a terminal node
//    //03          return the heuristic value of node
//    //04      if maximizingPlayer
//    //05          v := -∞
//    //06          each child of node
//    //07              v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
//    //08              α := max(α, v)
//    //09              if β ≤ α
//    //10                  break (* β cut-off *)
//    //11          return v
//    //12      else
//    //13          v := ∞
//    //14          for each child of node
//    //15              v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
//    //16              β := min(β, v)
//    //17              if β ≤ α
//    //18                  break (* α cut-off *)
//    //19          return v
//    public int aplfaBeta(int alpha, int beta, int depth, Board board, boolean maximizingPlayer) {
//
//        //if (pos.isGameOver()) {
//        //    System.out.println("Game is over");
//        //    return 0;
//        //}
//        if ((nodes % 1024) == 0) {
//            checkForTimeIsUp(board);
//        }
//        //System.out.println("Node: " + nodes + "\t alpha: " + alpha + " \t beta: " + beta + "\t depth: " + depth + "\t");
//
//        if (depth == 0) {
//            nodes++;
//            return evaluation.evaluateBoard(board);
//        }
//
//        ArrayList<Move> moveList = moveGen.generateAllMoves(board);
//        if (maximizingPlayer) {
//            int v = -infinite;
//            for (Move childMove : moveList) {
//                board.makeMove(childMove);
//                v = max(v, aplfaBeta(alpha, beta, depth - 1, board, false)); //v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
//                board.undoLastMove();
//                alpha = max(alpha, v);
//                if (beta <= alpha) {
//                    System.out.println("cut-off white node: " + nodes);
//                    bestMove = childMove;
//                    return beta;
//                    //break; // cut-off
//                }
//            }
//            return v;
//        } else {
//            int v = infinite;
//            for (Move childMove : moveList) {
//                board.makeMove(childMove);
//                v = min(v, aplfaBeta(alpha, beta, depth - 1, board, true)); //v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
//                board.undoLastMove();
//                beta = min(beta, v);
//                if (beta <= alpha) {
//                    System.out.println("cut-off black node: " + nodes);
//                    bestMove = childMove;
//                    return alpha;
//                    //break; // cut-off
//                }
//            }
//            return v;
//        }
//    }
    /**
     * iterative deepening
     *
     * @param board
     */
    public void FindBedstMove(Board board) {
        long startTime = System.currentTimeMillis();
        int maxDepth = board.getSearchDepth();
        int currentDepth;
        board.setStartTime();

        alphaBetaMax(-infinite, infinite, maxDepth, board);

//        for (currentDepth = 0; currentDepth < maxDepth; currentDepth++) {
//            if (pos.isGameOver()) {
//                break;
//            }
////            int bestScore = aplfaBeta(-infinite, infinite, currentDepth, pos, true);
//            int bestScore = aplfaBetaTest(-infinite, infinite, currentDepth, pos);
//
//            //check for time is up
//            System.out.println("---------------------------------------------------------");
//            System.out.println("---------------------------------------------------------");
//            System.out.println("iterative deepening(" + currentDepth + "):" + bestScore);
//            System.out.println("---------------------------------------------------------");
//            System.out.println("---------------------------------------------------------");
//            if (bestMove != null) {
//                System.out.println("Bedst move: " + bestMove.toString());
//            }
//            double now = (((double) (System.currentTimeMillis() - startTime)) / 1000);
//            System.out.println("Time: " + now + " sec");
//            System.out.println("---------------------------------------------------------");
//            System.out.println("---------------------------------------------------------");
//        }
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
