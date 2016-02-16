/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Objects.Board;
import chess.Objects.Fen;
import chess.Objects.Move;
import java.util.ArrayList;

/**
 *
 * @author msoede
 */
public class Perf {

    private boolean debugMode;
    private Board board;
    private Fen fen;
    private MoveGen moveGen;

    public Perf(boolean debugMode) {
        this.debugMode = debugMode;

        board = new Board();
        fen = new Fen();
        moveGen = new MoveGen();
    }

    public void clearBoard() {
        board.clearBoard();
    }

    public boolean makeTest(String fenString, int expRes, int seachDepth) {
        fen.loadFen(fenString, board);
        int movesTotal = moveGen.generateAllForDepth(board, seachDepth);
        System.out.println("Number of moves done at depth " + seachDepth + ": " + movesTotal + " == " + expRes);
        return movesTotal == expRes;
    }
}
