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
import chess.Objects.Piece;
import java.util.ArrayList;

/**
 * @author Mikkel Soede
 * @version 1.0
 * @description
 * @date
 */
public class MoveGen {

    private final Validate validate;
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
                if (p != null) { //make sure the piece is not empty
                    if (p.getType() == 'p' && !pos.isSide()) { //white side
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 2, file, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false));

                        //promotion??
                        if (file == 0) {

                        }
                    } else if (p.getType() == 'P' && pos.isSide()) { //white side
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file, false, false, false, false, false, false)); //normal move forward
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 2, file, false, false, false, false, false, false)); //doobelt move forward
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file - 1, false, false, false, false, false, false)); //attack move forward
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false)); //attach move forward
                        //promotion??
                        if (file == 7) {

                        }
                    } else if (p.getType() == 'N' || p.getType() == 'n') {
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 2, file + 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 2, file - 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file + 2, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file - 2, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file - 2, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 2, file + 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 2, file - 1, false, false, false, false, false, false));
                    } else if (p.getType() == 'R' || p.getType() == 'r') {
                        for (int i = 0; i < 8; i++) {
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, i, file, false, false, false, false, false, false));
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, i, false, false, false, false, false, false));
                        }
                    } else if (p.getType() == 'B' || p.getType() == 'b') {
                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank + i), (file + i), false, false, false, false, false, false));
                            if ((rank + i) >= 8 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank - i), (file - i), false, false, false, false, false, false));
                            if ((rank - i) <= 0 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank - i), (file + i), false, false, false, false, false, false));
                            if ((rank - i) <= 0 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank + i), (file - i), false, false, false, false, false, false));
                            if ((rank + i) >= 8 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }

                    } else if (p.getType() == 'Q' || p.getType() == 'q') {
                        //horicontial and verical
                        for (int i = 0; i < 8; i++) {
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, i, file, false, false, false, false, false, false));
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, i, false, false, false, false, false, false));
                        }

                        //diagonal
                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank + i), (file + i), false, false, false, false, false, false));
                            if ((rank + i) >= 8 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank - i), (file - i), false, false, false, false, false, false));
                            if ((rank - i) <= 0 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank - i), (file + i), false, false, false, false, false, false));
                            if ((rank - i) <= 0 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(pos, moveList, new Move(rank, file, (rank + i), (file - i), false, false, false, false, false, false));
                            if ((rank + i) >= 8 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }
                    } else if (p.getType() == 'K' || p.getType() == 'k') {
                        //Normal moves:
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, file - 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, file + 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank + 1, file - 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false));
                        validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false));

                        if (p.getType() == 'K') {  //Castling Moves white
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, file - 2, false, true, false, false, false, true));
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, file + 2, false, false, false, true, false, true));
                        } //Castling Moves black
                        else {
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, file + 2, false, false, true, false, false, false));
                            validateMoveAndToMoveList(pos, moveList, new Move(rank, file, rank, file - 2, false, false, false, false, true, false));

                        }

                    }
                }
            }
        }
        return moveList;
    }

    public boolean validateMoveAndToMoveList(Board pos, ArrayList<Move> moveList, Move move) {

        //make sure the move is only on the list once
        for (Move childMove : moveList) {
            if (childMove.compareMove(move)) {
                System.out.println("Move are eques");
                return false;
            }
        }

        if (validate.validateMove(move, pos)) {
            moveList.add(move);
            return true;
        }
        return false;
    }
}
