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
    private final int[][] blackPosistions = new int[][]{
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

        int nrWhitePawns = 0;
        int nrBlackPawns = 0;
        int nrWhiteRook = 0;
        int nrBlackRook = 0;
        int nrWhiteBishop = 0;
        int nrBlackBishop = 0;
        int nrWhiteKnight = 0;
        int nrBlackKnight = 0;
        int nrWhiteQueen = 0;
        int nrBlackQueen = 0;
        int nrWhiteKing = 0;
        int nrBlackKing = 0;

        int materialScore;

        int i_t = 8;
        int j_t = 8;

        int score = 0;
        for (int i = 0; i < 8; i++) {
            i_t--;
            for (int j = 0; j < 8; j++) {
                j_t--;
                Piece tempPiece = board.getPiece(i, j);
                if (board.getPiece(i, j) == null) {
                    continue; // empty piece
                }
                String pieceName = tempPiece.getName();
                boolean pieceColor = tempPiece.getPlayerColor();

                int tempScore = 0;
                switch (pieceName) {
                    case "Pawn":
                        if (pieceColor) {
//                            System.out.println("(" + i + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " pawnScore:" + pawnScore[i][j]);
                            tempScore = pawnScore[i][j];
                            nrWhitePawns++;
                        } else {
//                            System.out.println("(" + i_t + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " pawnScore:" + pawnScore[i_t][j]);
                            tempScore = -pawnScore[i_t][j];
                            nrBlackPawns++;
                        }
                        break;
                    case "Queen":
                        if (pieceColor) {
//                            System.out.println("queen (" + i + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " queenScore:" + queenScore[i][j]);
                            tempScore = queenScore[i][j];
                            nrWhiteQueen++;
                        } else {
//                            System.out.println("queen (" + i_t + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " queenScore:" + queenScore[i_t][j]);
                            tempScore = -queenScore[i_t][j];
                            nrBlackQueen++;
                        }
                        break;
                    case "King":
                        if (pieceColor) {
                            tempScore = kingScore[i][j];
                            nrWhiteKing++;
                        } else {
                            tempScore = -kingScore[i_t][j];
                            nrBlackKing++;
                        }
                        break;
                    case "Bishop":
                        if (pieceColor) {
//                            System.out.println("bishop (" + i + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " bishopscore:" + bishopScore[i][j]);
                            tempScore = bishopScore[i][j];
                            nrWhiteBishop++;
                        } else {
//                            System.out.println("bishop (" + i_t + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " bishopscore:" + bishopScore[i_t][j]);
                            tempScore = -bishopScore[i_t][j];
                            nrBlackBishop++;
                        }
                        break;
                    case "Knight":
                        if (pieceColor) {
//                            System.out.println("knight (" + i + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " knightScore:" + knightScore[i][j]);
                            tempScore = knightScore[i][j];
                            nrWhiteKnight++;
                        } else {
//                            System.out.println("knight (" + i_t + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " knightScore:" + knightScore[i_t][j]);
                            tempScore = -knightScore[i_t][j];
                            nrBlackKnight++;
                        }
                        break;
                    case "Rook":
                        if (pieceColor) {
//                            System.out.println("rook (" + i + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " rookscore:" + rookScore[i][j]);
                            tempScore = rookScore[i][j];
                            nrWhiteRook++;
                        } else {
//                            System.out.println("rook (" + i_t + "," + j + ") " + tempPiece.getName() + " color: " + tempPiece.getColorString() + " rookscore:" + rookScore[i_t][j]);
                            tempScore = -rookScore[i_t][j];
                            nrBlackRook++;
                        }
                        break;
                }
//                System.out.println(score + "+" + tempScore + "=" + (tempScore + score)+"  piece: "+pieceName+" "+pieceColor);
                score += tempScore;
            }
            j_t = 8;
        }
        int whoToMove = board.isSide() ? 1 : 1;

        materialScore = kingValue * (nrWhiteKing - nrBlackKing)
                + queenValue * (nrWhiteQueen - nrBlackQueen)
                + rookValue * (nrWhiteRook - nrBlackRook)
                + knightValue * (nrWhiteKnight - nrBlackKnight)
                + bishopValue * (nrWhiteBishop - nrBlackBishop)
                + pawnValue * (nrWhitePawns - nrBlackPawns);

        return (materialScore + score) * whoToMove;
    }
}
