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
    public int aplfaBetaTest(int alpha, int beta, int depth, Board pos) {
        int bestscore = -infinite;
        if (depth == 0) {
            return evaluation.evaluateBoard(pos);
        }
        ArrayList<Move> moveList = moveGen.generateAllMoves(pos);
        for (Move childMove : moveList) {
            pos.makeMove(childMove);
            int score = -aplfaBetaTest(-beta, -alpha, depth - 1, pos);
            pos.undoMove(childMove);
            if (score >= beta) {
                return score;  // fail-soft beta-cutoff
            }
            if (score > bestscore) {
                bestscore = score;
                if (score > alpha) {
                    alpha = score;
                }
            }
        }
        return bestscore;
    }

    // source: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
    //
    //01 function alphabeta(node, depth, α, β, maximizingPlayer)
    //02      if depth = 0 or node is a terminal node
    //03          return the heuristic value of node
    //04      if maximizingPlayer
    //05          v := -∞
    //06          each child of node
    //07              v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
    //08              α := max(α, v)
    //09              if β ≤ α
    //10                  break (* β cut-off *)
    //11          return v
    //12      else
    //13          v := ∞
    //14          for each child of node
    //15              v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
    //16              β := min(β, v)
    //17              if β ≤ α
    //18                  break (* α cut-off *)
    //19          return v
    public int aplfaBeta(int alpha, int beta, int depth, Board pos, boolean maximizingPlayer) {

        //if (pos.isGameOver()) {
        //    System.out.println("Game is over");
        //    return 0;
        //}
        if ((nodes % 1024) == 0) {
            checkForTimeIsUp(pos);
        }
        //System.out.println("Node: " + nodes + "\t alpha: " + alpha + " \t beta: " + beta + "\t depth: " + depth + "\t");

        if (depth == 0) {
            nodes++;
            return evaluation.evaluateBoard(pos);
        }

        ArrayList<Move> moveList = moveGen.generateAllMoves(pos);
        if (maximizingPlayer) {
            int v = -infinite;
            for (Move childMove : moveList) {
                pos.makeMove(childMove);
                v = max(v, aplfaBeta(alpha, beta, depth - 1, pos, false)); //v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
                pos.undoMove(childMove);
                alpha = max(alpha, v);
                if (beta <= alpha) {
                    System.out.println("cut-off white node: " + nodes);
                    bestMove = childMove;
                    return beta;
                    //break; // cut-off
                }
            }
            return v;
        } else {
            int v = infinite;
            for (Move childMove : moveList) {
                pos.makeMove(childMove);
                v = min(v, aplfaBeta(alpha, beta, depth - 1, pos, true)); //v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
                pos.undoMove(childMove);
                beta = min(beta, v);
                if (beta <= alpha) {
                    System.out.println("cut-off black node: " + nodes);
                    bestMove = childMove;
                    return alpha;
                    //break; // cut-off
                }
            }
            return v;
        }
    }

    /**
     * iterative deepening
     *
     * @param pos
     */
    public void FindBedstMove(Board pos) {
        long startTime = System.currentTimeMillis();
        int maxDepth = pos.getSearchDepth();
        int currentDepth;
        pos.setStartTime();

        for (currentDepth = 0; currentDepth < maxDepth; currentDepth++) {
            if (pos.isGameOver()) {
                break;
            }
//            int bestScore = aplfaBeta(-infinite, infinite, currentDepth, pos, true);
            int bestScore = aplfaBetaTest(-infinite, infinite, currentDepth, pos);

            //check for time is up
            System.out.println("---------------------------------------------------------");
            System.out.println("---------------------------------------------------------");
            System.out.println("iterative deepening(" + currentDepth + "):" + bestScore);
            System.out.println("---------------------------------------------------------");
            System.out.println("---------------------------------------------------------");
            if (bestMove != null) {
                System.out.println("Bedst move: " + bestMove.toString());
            }
            double now = (((double) (System.currentTimeMillis() - startTime)) / 1000);
            System.out.println("Time: " + now + " sec");
            System.out.println("---------------------------------------------------------");
            System.out.println("---------------------------------------------------------");
        }
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
