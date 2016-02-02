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

    public AlfaBetaSearch() {
        this.moveGen = new MoveGen();
        this.evaluation = new Evaluation();
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
    public int aplfaBeta(int alpha, int beta, int depth, Board pos) {

        if (depth == 0) {
            nodes++;
//            System.out.println("Nodes: " + nodes);
            return evaluation.evaluateBoard(pos);
        }

        if (nodes % 10000 == 0) {
            checkForTimeIsUp();
        }

        ArrayList<Move> moveList = moveGen.generateAllMoves(pos);
        System.out.println("Node: " + nodes + "\t alpha: " + alpha + " \t beta: " + beta + "\t depth: " + depth + "\t movelist size: " + moveList.size());
//        System.out.println("Depth: " + depth);
        if (pos.isSide()) {
            int v = -infinite;
            for (Move childMove : moveList) {
                pos.makeMove(childMove);
                v = max(v, aplfaBeta(alpha, beta, depth - 1, pos)); //v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
                pos.undoMove(childMove);
                //pos.undoLastMove();
                alpha = max(alpha, v);
                if (beta <= alpha) {
                    System.out.println("cut-off white");
                    break; // cut-off
                }
            }
            return v;
        } else {
            int v = infinite;
            for (Move childMove : moveList) {
                pos.makeMove(childMove);
                v = min(v, aplfaBeta(alpha, beta, depth - 1, pos)); //v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
                pos.undoMove(childMove);
//                pos.undoLastMove();
                beta = min(beta, v);
                if (beta <= alpha) {
                    System.out.println("cut-off black");
                    break; // cut-off
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
        int maxDepth = pos.getSearchDepth();
        int timeInSec = pos.getSearchTime();
        int currentDepth;
        Move bestMove;
        for (currentDepth = 0; currentDepth < maxDepth; currentDepth++) {
            int bestScore = aplfaBeta(-infinite, infinite, currentDepth, pos);
            //check for time is up
            System.out.println("---------------------------------------------------------");
            System.out.println("---------------------------------------------------------");
            System.out.println("iterative deepening(" + currentDepth + "):" + bestScore);
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

    private void checkForTimeIsUp() {

    }
}
