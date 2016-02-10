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
package chess.Objects;

import chess.Objects.Board;
import chess.Objects.Piece;

/**
 * @author Mikkel Soede
 * @version 1.0
 * @description
 * @date
 */
public class Fen {

    /**
     * e.g. rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
     *
     * @param fen
     * @param board
     * @return
     */
    public Board loadFen(String fen, Board board) {
        board.clearBoard(); // remove all pieces on board
        int rank = 7;
        int file = 0;
        int i;
        System.out.println("fen string: " + fen);
        for (i = 0; i < fen.length(); i++) {
            char currentPiece = fen.charAt(i);
            if (currentPiece == ' ') {
                break;
            }
            if (currentPiece == '/') {
                rank--;
                file = 0;
                continue;
            }

            int tempInt = currentPiece - '0';
            if (tempInt >= 0 && tempInt <= 8) {
                file = file + tempInt;
                continue;
            } else if (tempInt >= 17 && tempInt <= 42) { // capital letters (WHITE)
                board.setPiece(rank, file, new Piece(currentPiece, board.getPlayerWhite()));
            } else {//small letters (BLACK)
                board.setPiece(rank, file, new Piece(currentPiece, board.getPlayerBlack()));
            }
            file++;
        }
i++;
        board.setSide((fen.charAt(i) == 'w'));
        board.printBoard();

        //castling???
        //enpassant
        //turns
        getFen(board);
        return board;
    }

    /**
     *
     * e.g. rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
     *
     * @param board
     * @return
     */
    public String getFen(Board board) {
        String fenString;
        String pieces = "";
        String side;
        String castling;
        String enPassant;
        String hafMoves;
        String fullMoves;

        int spaces = 0;

        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file <= 7; file++) {
                Piece p = board.getPiece(rank, file);
                if (p == null) {
                    spaces++;
                } else if (spaces == 0) {
                    pieces = pieces + p.getType();
                } else {
                    pieces = pieces + "" + spaces + "" + p.getType();
                    spaces = 0;
                }
            }
            if (spaces != 0) {
                pieces = pieces + "" + spaces;
                spaces = 0;
            }
            if (rank != 0) {
                pieces = pieces + "/";
            }
        }

        side = board.isSide() ? "w" : "b";
        //castling???
        castling = "-";
        //enpassant
        enPassant = "-";

        hafMoves = "" + board.getHafMoves();
        fullMoves = "" + (board.getHafMoves() * 2);
        fenString = pieces + " " + side + " " + castling + " " + enPassant + " " + hafMoves + " " + fullMoves;
        return fenString;
    }
}
