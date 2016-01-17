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
public class MoveGen {

    //piece values:
    private final int pawnValue = 100;
    private final int knightValue = 350;
    private final int bishopValue = 350;
    private final int rookValue = 525;
    private final int queenValue = 1000;
    private final int kingValue = 50000;

    /**
     * generate all possible moves from a given position
     *
     * @param pos
     * @return
     */
    public ArrayList<Move> generateAllMoves(Board pos) {

        ArrayList<Move> moveList = new ArrayList<>();

        //Move m = new Move(rookValue, rookValue, rookValue, rookValue, true, true, true, true, true, true)
        //check for pawn moves forward
        //check for pawn moves attack
        //check for pawn moves dobblet move
        //check for caslte perm
        //sliding pieces
        //non sliding pieces
        return moveList;
    }

    public void generateAllPawnMoves(Board pos) {
        ArrayList<Move> moveList = new ArrayList<>();

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece p = pos.getPiece(rank, file);
                if (p != null) {
                    if (p.getType().equals("p") && !pos.isSide()) {
                        Move move1 = new Move(rank, file, rank - 1, file, false, false, false, false, false, false); //normal move forward
                        Move move2 = new Move(rank, file, rank - 2, file, false, false, false, false, false, false); //doobelt move forward

                        if (file >= 1) {
                            Move move3 = new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false); //attack left
                            boolean res3 = validatePawnMove(pos, move3);

                            if (res3) {
                                moveList.add(move3);
                            }
                        }
                        if (file <= 6) {
                            Move move4 = new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false); //normal move forward
                            boolean res4 = validatePawnMove(pos, move4);

                            if (res4) {
                                moveList.add(move4);
                            }
                        }

                        boolean res1 = validatePawnMove(pos, move1);
                        boolean res2 = validatePawnMove(pos, move2);
                        if (res1) {
                            moveList.add(move1);
                        }
                        if (res2) {
                            moveList.add(move2);
                        }
                    } else if (p.getType().equals("P") && pos.isSide()) {
                        Move move1 = new Move(rank, file, rank + 1, file, false, false, false, false, false, false); //normal move forward
                        Move move2 = new Move(rank, file, rank + 2, file, false, false, false, false, false, false); //doobelt move forward

                        if (file >= 1) {
                            Move move3 = new Move(rank, file, rank + 1, file - 1, false, false, false, false, false, false); //normal move forward
                            boolean res3 = validatePawnMove(pos, move3);

                            if (res3) {
                                moveList.add(move3);
                            }
                        }
                        if (file <= 6) {
                            Move move4 = new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false); //normal move forward
                            boolean res4 = validatePawnMove(pos, move4);

                            if (res4) {
                                moveList.add(move4);
                            }
                        }

                        boolean res1 = validatePawnMove(pos, move1);
                        boolean res2 = validatePawnMove(pos, move2);
                        if (res1) {
                            moveList.add(move1);
                        }
                        if (res2) {
                            moveList.add(move2);
                        }
                    } else if (p.getType().equals("N") && pos.isSide()) {
                        
                    }
                    
                }
            }
        }
        System.out.println("movelist length: " + moveList.size());
        for (Move next : moveList) {
            System.out.println(next.toString());
        }

    }

    public boolean validatePawnMove(Board board, Move moveTomake) {
        boolean currentPlayer = board.getSide();

        int fromRank = moveTomake.getFromRank();
        int fromFile = moveTomake.getFromFile();
        int toRank = moveTomake.getToRank();
        int toFile = moveTomake.getToFile();

        if (board.getPiece(fromRank, fromFile) == null) {
            return false;
        }

        if (board.getPiece(fromRank, fromFile) != null && currentPlayer != board.getPiece(fromRank, fromFile).getPlayer().isColor()) {
            return false;
        }

        if (board.getPiece(toRank, toFile) != null && currentPlayer == board.getPiece(toRank, toFile).getPlayer().isColor()) {
            return false;
        }

        int rowDiff = fromRank - toRank;
        int colDiff = fromFile - toFile;
        //WHITE PAWN
        if (board.getPiece(fromRank, fromFile).getName().equals("Pawn") && currentPlayer) {
            if (fromRank != 1 && rowDiff <= -2) {
                return false;
            } else if (fromRank == 1 && rowDiff < -2) {
                return false;
            } else if (rowDiff >= 0) {
                return false;
            } else if (Math.abs(colDiff) > 1) {
                return false;
            } else if (Math.abs(colDiff) == 1 && rowDiff == -1 && board.getPiece(toRank, toFile) == null) {
                return false;
            } else if (rowDiff == -1 && colDiff == 0 && board.getPiece(toRank, toFile) != null && board.getPiece(toRank, toFile).getPlayer().isColor() == !currentPlayer) {
                return false;
            }
            return true;
        } //PAWN BLACK
        else if (board.getPiece(fromRank, fromFile).getName().equals("Pawn") && !currentPlayer) {
            if (fromRank != 6 && rowDiff >= 2) {
                return false;
            } else if (fromRank == 6 && rowDiff > 2) {
                return false;
            } else if (rowDiff <= 0) {
                return false;
            } else if (Math.abs(colDiff) > 1) {
                return false;
            } else if (Math.abs(colDiff) == 1 && rowDiff == 1 && board.getPiece(toRank, toFile) == null) {
                return false;

            } else if (rowDiff == 1 && colDiff == 0 && board.getPiece(toRank, toFile) != null && board.getPiece(toRank, toFile).getPlayer().isColor() == !currentPlayer) {
                return false;
            }
            return true;
        }
        return false;
    }

}
