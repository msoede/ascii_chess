/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author msoede
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
        board.clearBoard();
        int rank = 7;
        int file = 7;
        int i;
        System.out.println("fen string: " + fen);
        for (i = 0; i < fen.length(); i++) {
            char currentPiece = fen.charAt(i);
            if (currentPiece == ' ') {
                break;
            }
            if (currentPiece == '/') {
                rank--;
                file = 7;
                System.out.println("new rank!!");
                continue;
            }

            int tempInt = currentPiece - '0';
            if (tempInt >= 0 && tempInt <= 8) {
                System.out.println("(" + rank + "," + file + ") cp: " + currentPiece + " tempint: " + tempInt + " int");
                file = file - tempInt;
                continue;
            } else if (tempInt >= 17 && tempInt <= 42) { // capital letters (WHITE)
                board.setPiece(rank, file, new Piece(currentPiece, board.getPlayerWhite()));
                System.out.println("(" + rank + "," + file + ") cp: " + currentPiece + " tempint: " + tempInt + " white");
            } else {//small letters (BLACK)
                System.out.println("(" + rank + "," + file + ") cp: " + currentPiece + " tempint: " + tempInt + " black");
                board.setPiece(rank, file, new Piece(currentPiece, board.getPlayerBlack()));
            }
            file--;
        }

        //board.setSide((fen.charAt(i) == 'w'));
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
        String fenString = "";
        String pieces = "";
        String side = "";
        String castling = "";
        String enPassant = "";
        String hafMoves = "";
        String fullMoves = "";

        int spaces = 0;

        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 7; file >= 0; file--) {
                Piece p = board.getPiece(rank, file);
                if (p == null) {
                    spaces++;
                } else {
                    if (spaces == 0) {
                        pieces = pieces + p.getType();
                    } else {
                        pieces = pieces + "" + spaces + "" + p.getType();
                        spaces = 0;
                    }
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
