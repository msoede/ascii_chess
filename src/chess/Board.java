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
    private ArrayList<Move> moveHistory = new ArrayList<>();

    private int searchTime = 15; // default value
    private int searchDepth = 5; // default value
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
        board[7][0] = new Piece("Rook", playerBlack);
        board[7][1] = new Piece("Knight", playerBlack);
        board[7][2] = new Piece("Bishop", playerBlack);
        board[7][3] = new Piece("Queen", playerBlack);
        board[7][4] = new Piece("King", playerBlack);
        board[7][5] = new Piece("Bishop", playerBlack);
        board[7][6] = new Piece("Knight", playerBlack);
        board[7][7] = new Piece("Rook", playerBlack);

        board[6][0] = new Piece("Pawn", playerBlack);
        board[6][1] = new Piece("Pawn", playerBlack);
        board[6][2] = new Piece("Pawn", playerBlack);
        board[6][3] = new Piece("Pawn", playerBlack);
        board[6][4] = new Piece("Pawn", playerBlack);
        board[6][5] = new Piece("Pawn", playerBlack);
        board[6][6] = new Piece("Pawn", playerBlack);
        board[6][7] = new Piece("Pawn", playerBlack);
    }

    public void clearBoard() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                setPiece(rank, file, null);
            }

        }
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

    public ArrayList<Move> getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(ArrayList<Move> moveHistory) {
        this.moveHistory = moveHistory;
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

    public Player getCurrentPlayer() {
        return side ? playerWhite : playerBlack;
    }

    public void undoLastMove() {
        Move lastMove = moveHistory.get(moveHistory.size() - 1);
    }

    public boolean makeMove(Move move) {
        hafMoves++;
        int fromFile = move.getFromFile();
        int fromRank = move.getFromRank();
        int toFile = move.getToFile();
        int toRank = move.getToRank();

        boolean currentPlayer = side;

        if (getPiece(toRank, toFile) != null && getPiece(toRank, toFile).getPlayer().isColor() != currentPlayer) {
            System.out.println("You have taken the enemy's " + getPiece(toRank, toFile).getName() + "!");
            setPiece(toRank, toFile, getPiece(fromRank, fromFile));
            setPiece(fromRank, fromFile, null);
            return true;
        } else {
            System.out.println("Move successful!");
            setPiece(toRank, toFile, getPiece(fromRank, fromFile));
            setPiece(fromRank, fromFile, null);
            return true;
        }
    }

    public int getHafMoves() {
        return hafMoves;
    }

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

        System.out.println("");
        System.out.println("      A     B     C     D     E     F     G     H   ");
        System.out.println("   #################################################     +------------------+-------------+");
        System.out.println("   #     #     #     #     #     #     #     #     #     | Board infomation |    value    |");
        System.out.println(" 8 #  " + v[7][0] + "  #  " + v[7][1] + "  #  " + v[7][2] + "  #  " + v[7][3] + "  #  " + v[7][4] + "  #  " + v[7][5] + "  #  " + v[7][6] + "  #  " + v[7][7] + "  # 8   +------------------+-------------+");
        System.out.format("   #     #     #     #     #     #     #     #     #     | Turn             | %11s |\n", getCurrentPlayer().getName());
        System.out.println("   #################################################     | 50 move rule     | 99          |");
        System.out.println("   #     #     #     #     #     #     #     #     #     | Castling         | KQkq        |");
        System.out.println(" 7 #  " + v[6][0] + "  #  " + v[6][1] + "  #  " + v[6][2] + "  #  " + v[6][3] + "  #  " + v[6][4] + "  #  " + v[6][5] + "  #  " + v[6][6] + "  #  " + v[6][7] + "  # 7   +------------------+-------------+");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println("   #################################################");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println(" 6 #  " + v[5][0] + "  #  " + v[5][1] + "  #  " + v[5][2] + "  #  " + v[5][3] + "  #  " + v[5][4] + "  #  " + v[5][5] + "  #  " + v[5][6] + "  #  " + v[5][7] + "  # 6   +--------+-------+-------+");
        System.out.println("   #     #     #     #     #     #     #     #     #     | piece  | White | Black |");
        System.out.println("   #################################################     +--------+-------+-------+");
        System.out.println("   #     #     #     #     #     #     #     #     #     | Pawn   | P     | p     |");
        System.out.println(" 5 #  " + v[4][0] + "  #  " + v[4][1] + "  #  " + v[4][2] + "  #  " + v[4][3] + "  #  " + v[4][4] + "  #  " + v[4][5] + "  #  " + v[4][6] + "  #  " + v[4][7] + "  # 5   | Knight | N     | n     |");
        System.out.println("   #     #     #     #     #     #     #     #     #     | Bishop | B     | b     |");
        System.out.println("   #################################################     | Rock   | R     | r     |");
        System.out.println("   #     #     #     #     #     #     #     #     #     | Queen  | Q     | q     |");
        System.out.println(" 4 #  " + v[3][0] + "  #  " + v[3][1] + "  #  " + v[3][2] + "  #  " + v[3][3] + "  #  " + v[3][4] + "  #  " + v[3][5] + "  #  " + v[3][6] + "  #  " + v[3][7] + "  # 4   | King   | K     | k     |");
        System.out.println("   #     #     #     #     #     #     #     #     #     +--------+-------+-------+");
        System.out.println("   #################################################");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println(" 3 #  " + v[2][0] + "  #  " + v[2][1] + "  #  " + v[2][2] + "  #  " + v[2][3] + "  #  " + v[2][4] + "  #  " + v[2][5] + "  #  " + v[2][6] + "  #  " + v[2][7] + "  # 3");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println("   #################################################");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println(" 2 #  " + v[1][0] + "  #  " + v[1][1] + "  #  " + v[1][2] + "  #  " + v[1][3] + "  #  " + v[1][4] + "  #  " + v[1][5] + "  #  " + v[1][6] + "  #  " + v[1][7] + "  # 2");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println("   #################################################");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println(" 1 #  " + v[0][0] + "  #  " + v[0][1] + "  #  " + v[0][2] + "  #  " + v[0][3] + "  #  " + v[0][4] + "  #  " + v[0][5] + "  #  " + v[0][6] + "  #  " + v[0][7] + "  # 1");
        System.out.println("   #     #     #     #     #     #     #     #     #");
        System.out.println("   #################################################");
        System.out.println("      A     B     C     D     E     F     G     H   ");
    }

}
