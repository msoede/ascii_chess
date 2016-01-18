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

    private Validate validate;

    //piece values:
    private final int pawnValue = 100;
    private final int knightValue = 350;
    private final int bishopValue = 350;
    private final int rookValue = 525;
    private final int queenValue = 1000;
    private final int kingValue = 50000;

    public MoveGen() {
        this.validate = new Validate();
    }

    /**
     * generate all possible moves from a given position
     *
     * @param pos
     * @return
     */
    public ArrayList<Move> generateAllMoves(Board pos) {

        ArrayList<Move> moveList = new ArrayList<>();

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece p = pos.getPiece(rank, file);
                if (p != null) {
                    if (p.getType().equals("p") && !pos.isSide()) {
                        Move move1 = new Move(rank, file, rank - 1, file, false, false, false, false, false, false); //normal move forward
                        Move move2 = new Move(rank, file, rank - 2, file, false, false, false, false, false, false); //doobelt move forward
                        Move move3 = new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false); //attack left
                        Move move4 = new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false); //normal move forward

                        validateMoveAndToMoveList(pos, moveList, move1);
                        validateMoveAndToMoveList(pos, moveList, move2);
                        validateMoveAndToMoveList(pos, moveList, move3);
                        validateMoveAndToMoveList(pos, moveList, move4);
                    } else if (p.getType().equals("P") && pos.isSide()) {
                        Move move1 = new Move(rank, file, rank + 1, file, false, false, false, false, false, false); //normal move forward
                        Move move2 = new Move(rank, file, rank + 2, file, false, false, false, false, false, false); //doobelt move forward
                        Move move3 = new Move(rank, file, rank + 1, file - 1, false, false, false, false, false, false); //normal move forward
                        Move move4 = new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false); //normal move forward

                        validateMoveAndToMoveList(pos, moveList, move1);
                        validateMoveAndToMoveList(pos, moveList, move2);
                        validateMoveAndToMoveList(pos, moveList, move3);
                        validateMoveAndToMoveList(pos, moveList, move4);
                    } else if (p.getType().equals("N") || p.getType().equals("n")) {
                        Move move1 = new Move(rank, file, rank + 2, file + 1, false, false, false, false, false, false); //normal move forward
                        Move move2 = new Move(rank, file, rank + 2, file - 1, false, false, false, false, false, false); //doobelt move forward
                        Move move3 = new Move(rank, file, rank + 1, file + 2, false, false, false, false, false, false); //normal move forward
                        Move move4 = new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false); //normal move forward
                        Move move5 = new Move(rank, file, rank + 1, file - 2, false, false, false, false, false, false); //normal move forward
                        Move move6 = new Move(rank, file, rank - 1, file - 2, false, false, false, false, false, false); //normal move forward
                        Move move7 = new Move(rank, file, rank - 2, file + 1, false, false, false, false, false, false); //normal move forward
                        Move move8 = new Move(rank, file, rank - 2, file - 1, false, false, false, false, false, false); //normal move forward

                        validateMoveAndToMoveList(pos, moveList, move1);
                        validateMoveAndToMoveList(pos, moveList, move2);
                        validateMoveAndToMoveList(pos, moveList, move3);
                        validateMoveAndToMoveList(pos, moveList, move4);
                        validateMoveAndToMoveList(pos, moveList, move5);
                        validateMoveAndToMoveList(pos, moveList, move6);
                        validateMoveAndToMoveList(pos, moveList, move7);
                        validateMoveAndToMoveList(pos, moveList, move8);
                    } else if (p.getType().equals("R") || p.getType().equals("r")) {
                        for (int i = 0; i < 8; i++) {
                            Move move1 = new Move(rank, file, i, file, false, false, false, false, false, false);
                            validateMoveAndToMoveList(pos, moveList, move1);
                            Move move2 = new Move(rank, file, rank, i, false, false, false, false, false, false);
                            validateMoveAndToMoveList(pos, moveList, move2);
                        }

                    } else if (p.getType().equals("B") || p.getType().equals("b")) {
                        for (int i = 0; i < 8; i++) {
                            Move move1 = new Move(rank, file, i, i, false, false, false, false, false, false);
                            validateMoveAndToMoveList(pos, moveList, move1);
                            Move move2 = new Move(rank, file, i, i, false, false, false, false, false, false);
                            validateMoveAndToMoveList(pos, moveList, move2);
                        }

                    } else if (p.getType().equals("Q") || p.getType().equals("q")) {
//                        Move move1 = new Move(rank, file, rank + 2, file + 1, false, false, false, false, false, false); 
//                        validateMoveAndToMoveList(pos, moveList, move1);

                    } else if (p.getType().equals("K") || p.getType().equals("k")) {
                        Move move1 = new Move(rank, file, rank + 1, file, false, false, false, false, false, false); //normal move forward
                        Move move2 = new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false); //normal move forward
                        Move move3 = new Move(rank, file, rank, file - 1, false, false, false, false, false, false); //normal move forward
                        Move move4 = new Move(rank, file, rank, file + 1, false, false, false, false, false, false); //normal move forward
                        Move move5 = new Move(rank, file, rank, file - 1, false, false, false, false, false, false); //normal move forward
                        Move move6 = new Move(rank, file, rank - 1, file, false, false, false, false, false, false); //normal move forward
                        Move move7 = new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false); //normal move forward
                        Move move8 = new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false); //normal move forward
                        validateMoveAndToMoveList(pos, moveList, move1);
                        validateMoveAndToMoveList(pos, moveList, move2);
                        validateMoveAndToMoveList(pos, moveList, move3);
                        validateMoveAndToMoveList(pos, moveList, move4);
                        validateMoveAndToMoveList(pos, moveList, move5);
                        validateMoveAndToMoveList(pos, moveList, move6);
                        validateMoveAndToMoveList(pos, moveList, move7);
                        validateMoveAndToMoveList(pos, moveList, move8);
                    }
                }
            }
        }
        return moveList;
    }

    public void validateMoveAndToMoveList(Board pos, ArrayList<Move> moveList, Move move) {
        boolean res2 = validate.validateMove(move, pos);
        if (res2) {
            moveList.add(move);
        }
    }

    public boolean validatePawnMove(Board board, Move moveTomake) {
        boolean currentPlayer = board.getSide();

        int fromRank = moveTomake.getFromRank();
        int fromFile = moveTomake.getFromFile();
        int toRank = moveTomake.getToRank();
        int toFile = moveTomake.getToFile();

        if (fromRank <= 0 || fromRank >= 8 || fromFile <= 0 || fromFile >= 8 || toRank <= 0 || toRank >= 8 || toFile <= 0 || toFile >= 8) {
            return false;
        }

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
