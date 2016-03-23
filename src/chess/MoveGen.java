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

    public ArrayList<Move> generateAllMovesOppositeSide(Board board) {
        board.switchSide();
        ArrayList<Move> moveList = generateAllMoves(board, false);
        board.switchSide();
        return moveList;
    }

    public ArrayList<Move> generateAllMoves(Board board) {
        return generateAllMoves(board, true);
    }

    public ArrayList<Move> allMovesExptKing(Board board) {
        return generateAllMoves(board, false);
    }

    private ArrayList<Move> generateAllMoves(Board board, boolean doTheKing) {
        ArrayList<Move> moveList = new ArrayList<>();
        boolean playerColor = board.getSide();
        boolean pawnDoubleMove = true;

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece p = board.getPiece(rank, file);
                if (p != null) { //make sure the piece is not empty
                    if (p.getType() == 'p' && !board.isSide()) { //white side
                        //promotion
                        if ((rank - 1) == 0) {
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file, true, false, false, false, false, false, false, playerColor), true);
                            continue;
                        }
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 2, file, false, false, false, false, false, false, pawnDoubleMove, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false, false, playerColor), true);

                    } else if (p.getType() == 'P' && board.isSide()) { //white side
                        //promotion
                        if ((rank + 1) == 7) {
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file, true, false, false, false, false, false, false, playerColor), true);
                            continue;
                        }
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file, false, false, false, false, false, false, false, playerColor), true); //normal move forward
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 2, file, false, false, false, false, false, false, pawnDoubleMove, playerColor), true); //doobelt move forward
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file - 1, false, false, false, false, false, false, false, playerColor), true); //attack move forward
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false, false, playerColor), true); //attach move forward
                    } else if (p.getType() == 'N' || p.getType() == 'n') {
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file + 2, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file - 2, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 2, file + 1, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 2, file - 1, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file + 2, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file - 2, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 2, file + 1, false, false, false, false, false, false, false, playerColor), true);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 2, file - 1, false, false, false, false, false, false, false, playerColor), true);
                    } else if (p.getType() == 'R' || p.getType() == 'r') {
                        for (int i = 0; i < 8; i++) {
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, i, file, false, false, false, false, false, false, false, playerColor), true);
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, i, false, false, false, false, false, false, false, playerColor), true);
                        }
                    } else if (p.getType() == 'B' || p.getType() == 'b') {
                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank + i), (file + i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank + i) >= 8 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank - i), (file - i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank - i) <= 0 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank - i), (file + i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank - i) <= 0 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank + i), (file - i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank + i) >= 8 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }

                    } else if (p.getType() == 'Q' || p.getType() == 'q') {
                        //horicontial and verical
                        for (int i = 0; i < 8; i++) {
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, i, file, false, false, false, false, false, false, false, playerColor), true);
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, i, false, false, false, false, false, false, false, playerColor), true);
                        }

                        //diagonal
                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank + i), (file + i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank + i) >= 8 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank - i), (file - i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank - i) <= 0 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank - i), (file + i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank - i) <= 0 || (file + i) >= 8 || res == false) {
                                break;
                            }
                        }

                        for (int i = 1; i < 8; i++) {
                            boolean res = validateMoveAndToMoveList(board, moveList, new Move(rank, file, (rank + i), (file - i), false, false, false, false, false, false, false, playerColor), true);
                            if ((rank + i) >= 8 || (file - i) <= 0 || res == false) {
                                break;
                            }
                        }
                    } else if ((p.getType() == 'K' || p.getType() == 'k')) {

                        //Normal moves:
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file + 1, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, file - 1, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, file + 1, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank + 1, file - 1, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file - 1, false, false, false, false, false, false, false, playerColor), doTheKing);
                        validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank - 1, file + 1, false, false, false, false, false, false, false, playerColor), doTheKing);

                        if (p.getType() == 'K') {  //Castling Moves white
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, file + 2, false, false, true, false, false, false, false, playerColor), doTheKing);
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, file - 2, false, false, false, false, true, false, false, playerColor), doTheKing);
                        } //Castling Moves black
                        else {
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, file + 2, false, false, false, true, false, false, false, playerColor), doTheKing);
                            validateMoveAndToMoveList(board, moveList, new Move(rank, file, rank, file - 2, false, false, false, false, false, true, false, playerColor), doTheKing);

                        }

                    }
                }
            }
        }
        return moveList;
    }

    public boolean validateMove(Move moveTomake, Board board, boolean otherSide) {

        boolean currentPlayer = board.getSide();

        int fromRank = moveTomake.getFromRank();
        int fromFile = moveTomake.getFromFile();
        int toRank = moveTomake.getToRank();
        int toFile = moveTomake.getToFile();

        if (fromRank < 0 || fromRank >= 8 || fromFile < 0 || fromFile >= 8 || toRank < 0 || toRank >= 8 || toFile < 0 || toFile >= 8) {
            return false;
        }

        if (board.getPiece(fromRank, fromFile) == null) {
            return false;
        }

        if (board.getPiece(fromRank, fromFile) != null && currentPlayer != board.getPiece(fromRank, fromFile).getPlayerColor()) {
            return false;
        }

        if (board.getPiece(toRank, toFile) != null && currentPlayer == board.getPiece(toRank, toFile).getPlayerColor()) {
            return false;
        }

        int rowDiff = fromRank - toRank;
        int colDiff = fromFile - toFile;
        int absRowDiff = Math.abs(rowDiff);
        int absColDiff = Math.abs(colDiff);
        Piece fromPiece = board.getPiece(fromRank, fromFile);
        Piece toPiece = board.getPiece(toRank, toFile);
        String fromPieceName = "";
        String toPieceName = "";
        if (fromPiece != null) {
            fromPieceName = fromPiece.getName();
        }
        if (toPiece != null) {
            toPieceName = toPiece.getName();
        }

        //WHITE PAWN
        if (fromPieceName.equals("Pawn")) {
            //new pawn validater

            int pawnStartFromRank = currentPlayer ? 1 : 6;
            int pawnStartToRank = currentPlayer ? 3 : 4;
            int pawnPromotionRank = currentPlayer ? 7 : 1;

            if (fromRank == pawnStartFromRank && toRank == pawnStartToRank && colDiff == 0 && toPiece == null && board.getPiece(currentPlayer ? toRank - 1 : toRank + 1, toFile) == null) {
                return true; //doble move forward 
            } else if (moveTomake.isPromoted() && toRank == pawnPromotionRank && toPiece == null) {
                return true; //Promotion 
            } else if (absRowDiff == 1 && colDiff == 0 && toPiece == null) {
                return true; //move forward
            } else if (absRowDiff == 1 && absColDiff == 1 && toPiece != null && toPiece.getPlayerColor() == !currentPlayer) {
                return true; //attack move
            } else if (absRowDiff == 1 && absColDiff == 1 && toPiece == null && board.getEnPassFile() == toFile && board.getEnPassRank() == toRank) {
                return true; //attack enPassant square move
            } else {
                return false; //defualt value
            }
        } else if (fromPieceName.equals("King")) {
            boolean isKingAllowedToMove = true;
            if (otherSide) {
                board.setPiece(fromRank, fromFile, null); // remove the king from board to remove shadow move
                isKingAllowedToMove = !isInCheck(board, moveTomake);
                board.setPiece(fromRank, fromFile, fromPiece); // put the king back on the board
            }
            boolean isCastlingQueenSide = moveTomake.isCastleBlackQueen() || moveTomake.isCastleWhiteQueen();
            boolean isCastlingKingSide = moveTomake.isCastleBlackKing() || moveTomake.isCastleWhiteKing();
            if (isCastlingQueenSide && absRowDiff == 0 && absColDiff == 2 && board.isQueenSideCastlingAllowed(currentPlayer) && isKingAllowedToMove) { // castling 
                return true;
            } else if (isCastlingKingSide && absRowDiff == 0 && absColDiff == 2 && board.isKingSideCastlingAllowed(currentPlayer) && isKingAllowedToMove) { // castling 
                return true;
            } else if (absRowDiff > 1 || absColDiff > 1) { // moved to far
                return false;
            } else if ((absRowDiff == 1 || absColDiff == 1) && board.getEnPassFile() == toFile && board.getEnPassRank() == toRank) { // normal king move attack enPassant
                moveTomake.setEnPassantMove(true);
                return isKingAllowedToMove;
            } else { //normal move make sure the pice is not in check
                return isKingAllowedToMove;
            }
        } else if (fromPieceName.equals("Queen")) {
            if (absRowDiff != absColDiff && rowDiff != 0 && colDiff != 0) {
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff == 0 && colDiff > 0) {
                for (int i = fromFile - 1; i > toFile; i--) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = fromFile + 1; i < toFile; i++) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            }

            if (board.getEnPassFile() == toFile && board.getEnPassRank() == toRank) {
                moveTomake.setEnPassantMove(true);
                return true;
            }

            return true;
        } else if (fromPieceName.equals("Knight")) {
            if ((absRowDiff * absColDiff == 2) && board.getEnPassFile() == toFile && board.getEnPassRank() == toRank) {
                moveTomake.setEnPassantMove(true);
                return true;
            }
            return absRowDiff * absColDiff == 2;
        } else if (fromPieceName.equals("Bishop")) {
            if (absRowDiff != absColDiff) {
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                int f = fromFile - 1;
                for (int r = fromRank - 1; r > toRank; r--) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f--;
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                int f = fromFile + 1;
                for (int r = fromRank + 1; r < toRank; r++) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f++;
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                int f = fromFile + 1;
                for (int r = fromRank - 1; r > toRank; r--) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f++;
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                int f = fromFile - 1;
                for (int r = fromRank + 1; r < toRank; r++) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f--;
                }
            }

            if (board.getEnPassFile() == toFile && board.getEnPassRank() == toRank) {
                moveTomake.setEnPassantMove(true);
                return true;
            }

            return true;
        } else if (fromPieceName.equals("Rook")) {
            if (rowDiff != 0 && colDiff != 0) {
                return false;
            }

            if (rowDiff == 0 && colDiff > 0) {
                for (int i = fromFile - 1; i > toFile; i--) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = fromFile + 1; i < toFile; i++) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            }

            if (board.getEnPassFile() == toFile && board.getEnPassRank() == toRank) {
                moveTomake.setEnPassantMove(true);
                return true;
            }

            return true;
        }
        return false; // default value
    }

    public boolean isInCheck(Board board, Move move) {
        ArrayList<Move> moveList = generateAllMovesOppositeSide(board);
        for (Move childMove : moveList) {
            if (childMove.compareMoveTo(move)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateMoveAndToMoveList(Board pos, ArrayList<Move> moveList, Move move, boolean otherSide) {
        //make sure the move is only on the list once
//        for (Move childMove : moveList) {
//            if (childMove.compareMove(move)) {
//                System.out.println("Move are eques");
//                return false;
//            }
//        }

        if (validateMove(move, pos, otherSide)) {
            moveList.add(move);
            return true;
        }
        return false;
    }

    /**
     *
     * @param move format: "a2a3"
     * @param board
     * @paentPlayer
     * @return
     */
    public boolean validateMoveAndDoTheMove(String move, Board board) {
        int fromFile = 0;
        int fromRank = move.charAt(1) - '1';
        int toFile = 0;
        int toRank = move.charAt(3) - '1';

        for (int i = 0; i < 8; i++) {
            if (move.charAt(0) == ('a' + i)) {
                fromFile = i;
            }
            if (move.charAt(2) == ('a' + i)) {
                toFile = i;
            }
        }

        Move moveTomake = new Move(fromRank, fromFile, toRank, toFile, false, false, false, false, false, false, false, false);

        if (validateMove(moveTomake, board, true)) {
            board.makeMove(moveTomake);
            return true;
        }
        return false;
    }
}
