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
public class Board {

    private final Piece board[][];
    private boolean side;
    private final Player playerWhite;
    private final Player playerBlack;
    private final ArrayList<Move> moveHistory = new ArrayList<>();
    private int searchTime = 15; // default value
    private int searchDepth = 5; // default value
    private long startTime;
    private long endTime = 0;
    private boolean gameOver = false;
    private boolean humanPlayer = true; // default value
    private int hafMoves = 0;

    public Board(String whitePlayerName, String blackPlayerName) {
        //setup players
        playerWhite = new Player(whitePlayerName, true);
        playerBlack = new Player(blackPlayerName, false);
        side = playerWhite.isColor();

        //setup the board
        board = new Piece[8][8];
        //init all the square objects
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

    }

    /**
     * Set the normal start position on the board
     */
    public void setStartPosistion() {
        //empty the board
        clearBoard();

        // setup white side
        board[0][0] = new Piece("Rook", playerWhite);
        board[0][1] = new Piece("Knight", playerWhite);
        board[0][2] = new Piece("Bishop", playerWhite);
        board[0][3] = new Piece("Queen", playerWhite);
        board[0][4] = new Piece("King", playerWhite);
        board[0][5] = new Piece("Bishop", playerWhite);
        board[0][6] = new Piece("Knight", playerWhite);
        board[0][7] = new Piece("Rook", playerWhite);

        board[1][0] = new Piece("Pawn", playerWhite);
        board[1][1] = new Piece("Pawn", playerWhite);
        board[1][2] = new Piece("Pawn", playerWhite);
        board[1][3] = new Piece("Pawn", playerWhite);
        board[1][4] = new Piece("Pawn", playerWhite);
        board[1][5] = new Piece("Pawn", playerWhite);
        board[1][6] = new Piece("Pawn", playerWhite);
        board[1][7] = new Piece("Pawn", playerWhite);

        //setup black side
        board[6][0] = new Piece("Pawn", playerBlack);
        board[6][1] = new Piece("Pawn", playerBlack);
        board[6][2] = new Piece("Pawn", playerBlack);
        board[6][3] = new Piece("Pawn", playerBlack);
        board[6][4] = new Piece("Pawn", playerBlack);
        board[6][5] = new Piece("Pawn", playerBlack);
        board[6][6] = new Piece("Pawn", playerBlack);
        board[6][7] = new Piece("Pawn", playerBlack);

        board[7][0] = new Piece("Rook", playerBlack);
        board[7][1] = new Piece("Knight", playerBlack);
        board[7][2] = new Piece("Bishop", playerBlack);
        board[7][3] = new Piece("Queen", playerBlack);
        board[7][4] = new Piece("King", playerBlack);
        board[7][5] = new Piece("Bishop", playerBlack);
        board[7][6] = new Piece("Knight", playerBlack);
        board[7][7] = new Piece("Rook", playerBlack);
    }

    /**
     * clear all the pieces on the board
     */
    public void clearBoard() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                setPiece(rank, file, null);
            }
        }
    }

    /**
     * the start time to currentTimeMillis() and set the end to
     * currentTimeMillis() + the searchTime
     */
    public void setStartTime() {
        this.startTime = (System.currentTimeMillis());
        this.endTime = (System.currentTimeMillis() + (searchTime * 1000));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    public void setHumanPlayer(String humanPlayerSide) {
        this.humanPlayer = humanPlayerSide.equals("white");
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public int getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(int searchTime) {
        this.searchTime = searchTime;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public void switchSide() {
        side = !side;
    }

    public boolean getSide() {
        return side;
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }

    public Piece getPiece(int rank, int file) {
        return board[rank][file];
    }

    public void setPiece(int rank, int file, Piece piece) {
        board[rank][file] = piece;
    }

    public int getHafMoves() {
        return hafMoves;
    }

    public Player getCurrentPlayer() {
        return side ? playerWhite : playerBlack;
    }

    public void undoLastMove() {
        int size = moveHistory.size();
        if (size > 0) {
            Move lastMove = moveHistory.get(size - 1);
            moveHistory.remove(size - 1);
            undoMove(lastMove);
        }
    }

    private void undoMove(Move m) {
        int toRank = m.gtr();
        int fromRank = m.gfr();
        int toFile = m.gtf();
        int fromFile = m.gff();
        setPiece(fromRank, fromFile, getPiece(toRank, toFile));
        if (m.isCaputreMove()) {
            setPiece(toRank, toFile, new Piece(m.getType(), playerWhite));
        } else {
            setPiece(toRank, toFile, null);
        }

        hafMoves--;
    }

    /**
     *
     * @param move
     * @return true when move is done
     */
    public boolean makeMove(Move move) {
        hafMoves++;
        int fromFile = move.getFromFile();
        int fromRank = move.getFromRank();
        int toFile = move.getToFile();
        int toRank = move.getToRank();

        boolean currentPlayer = side;

        if (getPiece(toRank, toFile) != null && getPiece(toRank, toFile).getPlayer().isColor() != currentPlayer) {
            setPiece(toRank, toFile, getPiece(fromRank, fromFile));
            setPiece(fromRank, fromFile, null);
            move.setCaputreMove(getPiece(toRank, toFile).getName());
            moveHistory.add(move);
            return true;
        } else {
            setPiece(toRank, toFile, getPiece(fromRank, fromFile));
            setPiece(fromRank, fromFile, null);
            moveHistory.add(move);
            return true;
        }
    }
    
    /**
     * <h1>Prints an ASCII art view of the current chess board </h1>
     */
    public void printBoard() {
        String v[][] = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPiece(i, j) != null) {
                    v[i][j] = this.getPiece(i, j).getType();
                } else {
                    v[i][j] = " ";
                }
            }
        }

        String[][] fill = new String[8][4];

        fill[7][0] = "+------------------+-------------+";
        fill[7][1] = "| Board infomation |    value    |";
        fill[7][2] = "+------------------+-------------+";
        fill[7][3] = "| Turn             | " + String.format("%-11s", getCurrentPlayer().getName()) + " |";
        fill[6][0] = "| 50 move rule     | ??          |";
        fill[6][1] = "| Castling         | ????        |";
        fill[6][2] = "| move his size    | " + String.format("%-11d", moveHistory.size()) + " |";
        fill[6][3] = "| Search depth     | " + String.format("%-11d", getSearchDepth()) + " |";
        fill[5][0] = "| Search time      | " + String.format("%-11s", (getSearchTime() + " sec")) + " |";
        fill[5][1] = "| Haf moves        | " + String.format("%-11d", getHafMoves()) + " |";
        fill[5][2] = "| Full moves       | " + String.format("%-11d", (getHafMoves() / 2)) + " |";
        fill[5][3] = "+------------------+-------------+";
        fill[4][0] = "";
        fill[4][1] = "";
        fill[4][2] = "";
        fill[4][3] = "";

        fill[3][0] = "";
        fill[3][1] = "";
        fill[3][2] = "";
        fill[3][3] = "";
        fill[2][0] = "";
        fill[2][1] = "";
        fill[2][2] = "+--------+-------+-------+";
        fill[2][3] = "| piece  | White | Black |";
        fill[1][0] = "+--------+-------+-------+";
        fill[1][1] = "| Pawn   | P     | p     |";
        fill[1][2] = "| Knight | N     | n     |";
        fill[1][3] = "| Bishop | B     | b     |";
        fill[0][0] = "| Rock   | R     | r     |";
        fill[0][1] = "| Queen  | Q     | q     |";
        fill[0][2] = "| King   | K     | k     |";
        fill[0][3] = "+--------+-------+-------+";

        System.out.println("");
        System.out.println("      A     B     C     D     E     F     G     H   ");
        for (int i = 7; i >= 0; i--) {
            System.out.println("   #################################################    " + fill[i][0]);
            System.out.println("   #     #     #     #     #     #     #     #     #    " + fill[i][1]);
            System.out.println(" " + (i + 1) + " #  " + v[i][0] + "  #  " + v[i][1] + "  #  " + v[i][2] + "  #  " + v[i][3] + "  #  " + v[i][4] + "  #  " + v[i][5] + "  #  " + v[i][6] + "  #  " + v[i][7] + "  # " + (i + 1) + "  " + fill[i][2]);
            System.out.format("   #     #     #     #     #     #     #     #     #    " + fill[i][3] + "\n");
        }
        System.out.println("   #################################################");
        System.out.println("      A     B     C     D     E     F     G     H   ");

    }
}
