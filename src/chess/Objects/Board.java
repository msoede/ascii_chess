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

import chess.Evaluation;
import chess.Objects.*;
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
    private int searchDepth = 10; // default value
    private long startTime;
    private long endTime = 0;
    private boolean gameOver = false;
    private boolean humanPlayer = true; // default value
    private int hafMoves = 0;
    private boolean castlingWhite;
    private boolean castlingBlack;
    private boolean castlingWhiteKing;
    private boolean castlingWhiteQueen;
    private boolean castlingBlackKing;
    private boolean castlingBlackQueen;
    private int[] enPassantPiece;

    public Board() {
        this.enPassantPiece = new int[2];  // enPassantPiece[0] = rank      enPassantPiece[1] = file
        this.enPassantPiece[0] = -1;  // off board value
        this.enPassantPiece[1] = -1;  // off board value

        this.castlingBlack = true;
        this.castlingWhite = true;

        //setup players
        playerWhite = new Player(true);
        playerBlack = new Player(false);
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

    public void setEnpassantPiece(int rank, int file) {
        this.enPassantPiece[0] = rank;
        this.enPassantPiece[1] = file;
    }

    public void clearEnpassantPiece() {
        this.enPassantPiece[0] = -1;  // off board value
        this.enPassantPiece[1] = -1;  // off board value
    }

    /**
     *
     * @return true if enpassant piece is sat, false if enpassant piece NOT is
     * sat
     */
    public boolean isEnpassantPieceSat() {
        return !(this.enPassantPiece[0] == -1 && this.enPassantPiece[1] == -1);
    }

    public String getEnPassantString() {
        if (isEnpassantPieceSat()) {
            char[] file = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};
            char[] rank = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
            return rank[enPassantPiece[0]] + "" + file[enPassantPiece[1]];
        } else {
            return "none";
        }
    }

    /**
     * Set the normal start position on the board
     */
    public void setStartPosistion() {
        //empty the board
        clearBoard();
        //load start board
        Fen fen = new Fen();
        fen.loadFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", this);
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

    public boolean isKingSideCastlingAllowed(boolean side) {
        int startRank = side ? 0 : 7;
        char kingChar = side ? 'K' : 'k';
        char rookChar = side ? 'R' : 'r';
        Piece pieceKing = getPiece(startRank, 4);
        Piece pieceRookRight = getPiece(startRank, 7);

        boolean king = !(pieceKing == null);
        boolean rookRigth = !(pieceRookRight == null);
        boolean piecesOnTheRight = getPiece(startRank, 6) == null && getPiece(startRank, 5) == null;

        if (king && rookRigth && piecesOnTheRight) {
            if (pieceKing.getType() == kingChar && pieceRookRight.getType() == rookChar) {
                return true;
            }
        }
        return false;
    }

    // The king and the chosen rook are on the player's first rank.[3]
    // - Neither the king nor the chosen rook has previously moved.
    // - There are no pieces between the king and the chosen rook.
    // - The king is not currently in check.
    // - The king does not pass through a square that is attacked by an enemy piece.[4]
    // - The king does not end up in check. (True of any legal move.)
    // make sure all requerment for castling is done
    public boolean isQueenSideCastlingAllowed(boolean side) {
        int startRank = side ? 0 : 7;
        char kingChar = side ? 'K' : 'k';
        char rookChar = side ? 'R' : 'r';
        Piece pieceKing = getPiece(startRank, 4);
        Piece pieceRookLeft = getPiece(startRank, 0);
        boolean king = !(pieceKing == null);
        boolean rookLeft = !(pieceRookLeft == null);
        boolean piecesOnTheLeft = getPiece(startRank, 1) == null && getPiece(startRank, 2) == null && getPiece(startRank, 3) == null;

        if (king && rookLeft && piecesOnTheLeft) {
            if (pieceKing.getType() == kingChar && pieceRookLeft.getType() == rookChar) {
                return true;
            }
        }
        return false;
    }

    public void setCastlingWhite(boolean castlingWhite) {
        this.castlingWhite = castlingWhite;
    }

    public void setCastlingBlack(boolean castlingBlack) {
        this.castlingBlack = castlingBlack;
    }

    public boolean isCastlingWhite() {
        return castlingWhite;
    }

    public boolean isCastlingBlack() {
        return castlingBlack;
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
        } else {
            System.out.println("history is empty");
        }
    }

    private void undoMove(Move m) {

        int toRank = m.gtr();
        int fromRank = m.gfr();
        int toFile = m.gtf();
        int fromFile = m.gff();
        Player playerSide = !m.isPlayerColor() ? playerWhite : playerBlack;
        setPiece(fromRank, fromFile, getPiece(toRank, toFile));
        if (m.isCastleWhiteKing()) { //castle white king
            setPiece(0, 7, getPiece(0, 5));
            setPiece(0, 5, null);
            setPiece(0, 4, getPiece(0, 6));
            setPiece(0, 6, null);
            castlingWhite = true;
        } else if (m.isCastleWhiteQueen()) {//castle white queen
            setPiece(0, 0, getPiece(0, 3));
            setPiece(0, 3, null);
            setPiece(0, 4, getPiece(0, 2));
            setPiece(0, 2, null);
            castlingWhite = true;
        } else if (m.isCastleBlackKing()) { //castle black king
            setPiece(7, 7, getPiece(7, 5));
            setPiece(7, 5, null);
            setPiece(7, 4, getPiece(7, 6));
            setPiece(7, 6, null);
            castlingBlack = true;
        } else if (m.isCastleBlackQueen()) { //castle black queen
            setPiece(7, 0, getPiece(7, 3));
            setPiece(7, 3, null);
            setPiece(7, 4, getPiece(7, 2));
            setPiece(7, 2, null);
            castlingBlack = true;
        }

        if (m.isCaputreMove()) { //capture move
            setPiece(toRank, toFile, new Piece(m.getCaputrePiece(), playerSide));
        } else if (m.isPromoted()) {
            setPiece(toRank, toFile, null);
            setPiece(fromRank, fromFile, new Piece("Pawn", playerSide));
        } else {
            setPiece(toRank, toFile, null); // non capture move(normal move)
        }
        hafMoves--;
        switchSide();
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

        if (getPiece(toRank, toFile) != null && getPiece(toRank, toFile).getPlayer().isColor() != currentPlayer && move.isPromoted() == false) { // capture move
            move.setCaputreMove(getPiece(toRank, toFile).getName());
            setPiece(toRank, toFile, getPiece(fromRank, fromFile));
            setPiece(fromRank, fromFile, null);
            moveHistory.add(move);
            switchSide();
            return true;
        } else {
            if (move.isCastling() == false && move.isPromoted() == false) {  // normal move
                setPiece(toRank, toFile, getPiece(fromRank, fromFile));
                setPiece(fromRank, fromFile, null);
            } else if (move.isPromoted()) { // promotion move
                setPiece(fromRank, fromFile, null);
                setPiece(toRank, toFile, new Piece("Queen", getCurrentPlayer()));
            } else if (move.isCastleWhiteKing() && castlingWhite) { //castle white king
                setPiece(0, 5, getPiece(0, 7));
                setPiece(0, 7, null);
                setPiece(0, 6, getPiece(0, 4));
                setPiece(0, 4, null);
                castlingWhite = false;
            } else if (move.isCastleWhiteQueen() && castlingWhite) {//castle white queen
                setPiece(0, 3, getPiece(0, 0));
                setPiece(0, 0, null);
                setPiece(0, 2, getPiece(0, 4));
                setPiece(0, 4, null);
                castlingWhite = false;
            } else if (move.isCastleBlackKing() && castlingBlack) { //castle black king
                setPiece(7, 5, getPiece(7, 7));
                setPiece(7, 7, null);
                setPiece(7, 6, getPiece(7, 4));
                setPiece(7, 4, null);
                castlingBlack = false;
            } else if (move.isCastleBlackQueen() && castlingBlack) { //castle black queen
                setPiece(7, 3, getPiece(7, 0));
                setPiece(7, 0, null);
                setPiece(7, 2, getPiece(7, 4));
                setPiece(7, 4, null);
                castlingBlack = false;
            } else {    // error with move
                System.out.println("ERROR in board.makeMove() moveinfo: " + move.toString());
            }
            moveHistory.add(move);
            switchSide();
            return true;
        }
    }

    public String getMOveHistoryString() {
        String output = "";
        for (int i = 0; i < moveHistory.size(); i++) {
            output += moveHistory.get(i).getMoveString() + "\n";
        }
        return output;
    }

    /**
     * <h1>Prints an ASCII art view of the current chess board </h1>
     */
    public void printBoard() {
        String v[][] = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPiece(i, j) != null) {
                    v[i][j] = "" + this.getPiece(i, j).getType();
                } else {
                    v[i][j] = " ";
                }
            }
        }

        int size = moveHistory.size();
        String lastMoveString = "";
        if (size > 0) {
            Move lastMove = moveHistory.get(size - 1);
            lastMoveString = lastMove.getMoveString();
        }

        String castlingString = "W: " + (castlingWhite ? "1" : "0") + "  B: " + (castlingBlack ? "1" : "0");
        String[][] fill = new String[8][4];

        Evaluation evaluation = new Evaluation();
        
        fill[7][0] = "+------------------+-------------+";
        fill[7][1] = "| Board infomation |    value    |";
        fill[7][2] = "+------------------+-------------+";
        fill[7][3] = "| Turn             | " + String.format("%-11s", getCurrentPlayer().getName()) + " |";
        fill[6][0] = "| 50 move rule     | ??          |";
        fill[6][1] = "| Castling         | " + String.format("%-11s", castlingString) + " |";
        fill[6][2] = "| move his size    | " + String.format("%-11d", moveHistory.size()) + " |";
        fill[6][3] = "| Search depth     | " + String.format("%-11d", getSearchDepth()) + " |";
        fill[5][0] = "| Search time      | " + String.format("%-11s", (getSearchTime() + " sec")) + " |";
        fill[5][1] = "| Haf moves        | " + String.format("%-11d", getHafMoves()) + " |";
        fill[5][2] = "| Full moves       | " + String.format("%-11d", (getHafMoves() / 2)) + " |";
        fill[5][3] = "| Last Move        | " + String.format("%-11s", lastMoveString) + " |";
        fill[4][0] = "| EnPassant is sat | " + String.format("%-11s", isEnpassantPieceSat()) + " |";
        fill[4][1] = "| EnPassant piece  | " + String.format("%-11s", getEnPassantString()) + " |";
        fill[4][2] = "| Score            | " + String.format("%-11d", evaluation.evaluateBoard(this)) + " |";
        fill[4][3] = "+------------------+-------------+";

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
