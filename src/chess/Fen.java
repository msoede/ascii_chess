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
     * @return
     */
    public Board loadFen(String fen) {
        Board board = new Board("White", "Black");

        int rank = 0;
        int file = 0;
        int i;
        for (i = 0; i < fen.length(); i++) {
            char currentPiece = fen.charAt(i);
            if (currentPiece == '/') {
                rank++;
                file = 0;
                continue;
            }

            file++;

            if (currentPiece == ' ') {
                break;
            }

        }

        board.setSide((fen.charAt(i) == 'w'));

        //castling???
        //enpassant
        //turns
        
        return board;
    }

}
