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
//         | |____| | | |  __/\__ \__ \         
//          \_____|_| |_|\___||___/___/   
//
//         | |____| | | |  __/\__ \__ \         
//          \_____|_| |_|\___||___/___/   
//
//         | |____| | | |  __/\__ \__ \         
//          \_____|_| |_|\___||___/___/   
//
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
