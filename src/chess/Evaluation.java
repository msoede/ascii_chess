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
import chess.Objects.Piece;

/**
 * @author Mikkel Soede
 * @version 1.0
 * @description
 * @date
 */
public class Evaluation {

    // pawn table
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  0 |  0 |  0 |  0 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //| 10 | 10 |  0 |-10 |-10 |  0 | 10 | 10 |
    //+----+----+----+----+----+----+----+----+
    //|  5 |  0 |  0 |  5 |  5 |  0 |  0 |  5 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 | 10 | 20 | 20 | 10 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //|  5 |  5 |  5 | 10 | 10 |  5 |  5 |  5 |
    //+----+----+----+----+----+----+----+----+
    //| 10 | 10 | 10 | 20 | 20 | 10 | 10 | 10 |
    //+----+----+----+----+----+----+----+----+
    //| 20 | 20 | 20 | 30 | 30 | 20 | 20 | 20 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  0 |  0 |  0 |  0 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    private final int[][] pawnScore = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {10, 10, 0, -10, -10, 0, 10, 10},
        {5, 0, 0, 5, 5, 0, 0, 5},
        {0, 0, 10, 20, 20, 10, 0, 0},
        {5, 5, 5, 10, 10, 5, 5, 5},
        {10, 10, 10, 20, 20, 10, 10, 10},
        {20, 20, 20, 30, 30, 20, 20, 20},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
    //knight table
    //+----+-----+----+----+----+----+-----+----+
    //|  0 | -10 |  0 |  0 |  0 |  0 | -10 | 0  |
    //+----+-----+----+----+----+----+-----+----+
    //|  0 |   0 |  0 |  5 |  5 |  0 |   0 |  0 |
    //+----+-----+----+----+----+----+-----+----+
    //|  0 |   0 | 10 | 10 | 10 | 10 |   0 |  0 |
    //+----+-----+----+----+----+----+-----+----+
    //|  0 |   0 | 10 | 20 | 20 | 10 |   5 |  0 |
    //+----+-----+----+----+----+----+-----+----+
    //|  5 |  10 | 15 | 20 | 20 | 15 |  10 |  5 |
    //+----+-----+----+----+----+----+-----+----+
    //|  5 |  10 | 10 | 20 | 20 | 10 |  10 |  5 |
    //+----+-----+----+----+----+----+-----+----+
    //|  0 |   0 |  5 | 10 | 10 |  5 |   0 |  0 |
    //+----+-----+----+----+----+----+-----+----+
    //|  0 |   0 |  0 |  0 |  0 |  0 |   0 |  0 |
    //+----+-----+----+----+----+----+-----+----+
    private final int[][] knightScore = new int[][]{
        {0, -10, 0, 0, 0, 0, -10, 0},
        {0, 0, 0, 5, 5, 0, 0, 0},
        {0, 0, 10, 10, 10, 10, 0, 0},
        {0, 0, 10, 20, 20, 10, 5, 0},
        {5, 10, 15, 20, 20, 15, 10, 5},
        {5, 10, 10, 20, 20, 10, 10, 5},
        {0, 0, 5, 10, 10, 5, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
    //bishop table
    //+----+----+-----+----+----+-----+----+----+
    //|  0 |  0 | -10 |  0 |  0 | -10 |  0 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 |  0 |   0 | 10 | 10 |   0 |  0 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 |  0 |  10 | 15 | 15 |  10 |  0 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 | 10 |  15 | 20 | 20 |  15 | 10 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 | 10 |  15 | 20 | 20 |  15 | 10 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 |  0 |  10 | 15 | 15 |  10 |  0 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 |  0 |   0 | 10 | 10 |   0 |  0 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    //|  0 |  0 |   0 |  0 |  0 |   0 |  0 |  0 |
    //+----+----+-----+----+----+-----+----+----+
    private final int[][] bishopScore = new int[][]{
        {0, 0, -10, 0, 0, -10, 0, 0},
        {0, 0, 0, 10, 10, 0, 0, 0},
        {0, 0, 10, 15, 15, 10, 0, 0},
        {0, 10, 15, 20, 20, 15, 10, 0},
        {0, 10, 15, 20, 20, 15, 10, 0},
        {0, 0, 10, 15, 15, 10, 0, 0},
        {0, 0, 0, 10, 10, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
    //rock table
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    //| 25 | 25 | 25 | 25 | 25 | 25 | 25 | 25 |
    //+----+----+----+----+----+----+----+----+
    //|  0 |  0 |  5 | 10 | 10 |  5 |  0 |  0 |
    //+----+----+----+----+----+----+----+----+
    private final int[][] rookScore = new int[][]{
        {0, 0, 5, 10, 10, 5, 0, 0},
        {0, 0, 5, 10, 10, 5, 0, 0},
        {0, 0, 5, 10, 10, 5, 0, 0},
        {0, 0, 5, 10, 10, 5, 0, 0},
        {0, 0, 5, 10, 10, 5, 0, 0},
        {0, 0, 5, 10, 10, 5, 0, 0},
        {25, 25, 25, 25, 25, 25, 25, 25},
        {0, 0, 5, 10, 10, 5, 0, 0}
    };
    //queen table
    //+---+---+---+---+---+---+---+---+
    //| 2 | 3 | 4 | 3 | 4 | 3 | 3 | 2 |
    //+---+---+---+---+---+---+---+---+
    //| 2 | 3 | 4 | 4 | 4 | 4 | 3 | 2 |
    //+---+---+---+---+---+---+---+---+
    //| 3 | 4 | 4 | 4 | 4 | 4 | 4 | 3 |
    //+---+---+---+---+---+---+---+---+
    //| 3 | 3 | 4 | 4 | 4 | 4 | 4 | 3 |
    //+---+---+---+---+---+---+---+---+
    //| 2 | 3 | 3 | 4 | 4 | 3 | 3 | 2 |
    //+---+---+---+---+---+---+---+---+
    //| 2 | 2 | 2 | 3 | 3 | 2 | 2 | 2 |
    //+---+---+---+---+---+---+---+---+
    //| 2 | 2 | 2 | 2 | 2 | 2 | 2 | 2 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    private final int[][] queenScore = new int[][]{
        {2, 3, 4, 3, 4, 3, 3, 2},
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 4, 4, 4, 4, 4, 3},
        {3, 3, 4, 4, 4, 4, 4, 3},
        {2, 3, 3, 4, 4, 3, 3, 2},
        {2, 2, 2, 3, 3, 2, 2, 2},
        {2, 2, 2, 2, 2, 2, 2, 2},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
    //king table
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    //| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    //+---+---+---+---+---+---+---+---+
    private final int[][] kingScore = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
    //piece values:
    private final int pawnValue = 100;
    private final int knightValue = 350;
    private final int bishopValue = 350;
    private final int rookValue = 525;
    private final int queenValue = 1000;
    private final int kingValue = 50000;

    /**
     * gives the board position score
     *
     * @param board
     * @return
     */
    public int evaluateBoard(Board board) {

        int wp = 0;
        int bp = 0;
        int wr = 0;
        int br = 0;
        int wb = 0;
        int bb = 0;
        int wn = 0;
        int bn = 0;
        int wq = 0;
        int bq = 0;
        int wk = 0;
        int bk = 0;

        int materialScore = 0;

        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece tempPiece = board.getPiece(i, j);
                if (board.getPiece(i, j) == null) {
                    continue; // empty piece
                }
                String pieceName = tempPiece.getName();
                boolean pieceColor = tempPiece.getPlayerColor();
                int tempScore = 0;
                switch (pieceName) {
                    case "Pawn":
                        tempScore = pawnScore[i][j];
                        if (pieceColor) {
                            wp++;
                        } else {
                            bp++;
                        }
                        break;
                    case "Queen":
                        tempScore = queenScore[i][j];
                        if (pieceColor) {
                            wq++;
                        } else {
                            bq++;
                        }
                        break;
                    case "King":
                        tempScore = kingScore[i][j];
                        if (pieceColor) {
                            wk++;
                        } else {
                            bk++;
                        }
                        break;
                    case "Bishop":
                        tempScore = bishopScore[i][j];
                        if (pieceColor) {
                            wb++;
                        } else {
                            bb++;
                        }
                        break;
                    case "Knight":
                        tempScore = knightScore[i][j];
                        if (pieceColor) {
                            wn++;
                        } else {
                            bn++;
                        }
                        break;
                    case "Rook":
                        tempScore = rookScore[i][j];
                        if (pieceColor) {
                            wr++;
                        } else {
                            br++;
                        }
                        break;
                }
                //score += pieceColor ? tempScore : -tempScore;
                score += tempScore;
                //System.out.println("Score(" + i + "," + j + ") piece: " + pieceName + "\t    color: " + pieceColor + " \tscore:" + tempScore + " total score: " + score);
            }
        }
        int whoToMove = board.isSide() ? 1 : -1;

        materialScore = kingValue * (wk - bk)
                + queenValue * (wq - bq)
                + rookValue * (wr - br)
                + knightValue * (wn - bn)
                + bishopValue * (wb - bb)
                + pawnValue * (wp - bp);
        return (materialScore) * whoToMove;
    }
}
