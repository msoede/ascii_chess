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

/**
 * @author Mikkel Soede
 * @version 1.0
 * @description
 * @date
 */
public class Move {

    private final int fromRank;
    private final int toRank;
    private final int fromFile;
    private final int toFile;
    private final boolean promoted;
    private final boolean castleWhiteKing;
    private final boolean castleBlackKing;
    private final boolean castleWhiteQueen;
    private final boolean castleBlackQueen;
    private final boolean playerColor;
    private boolean caputreMove;
    private String caputrePiece;
    private boolean enPassantMove;
    private boolean doublePawnMove;

    public Move(int fromRank, int fromFile, int toRank, int toFile, boolean promoted, boolean enPassant, boolean castleWhiteKing, boolean castleBlackKing, boolean castleWhiteQueen, boolean castleBlackQueen, boolean doublePawnMove, boolean playerColor) {
        this.fromRank = fromRank;
        this.toRank = toRank;
        this.fromFile = fromFile;
        this.toFile = toFile;
        this.promoted = promoted;
        this.castleWhiteKing = castleWhiteKing;
        this.castleBlackKing = castleBlackKing;
        this.castleWhiteQueen = castleWhiteQueen;
        this.castleBlackQueen = castleBlackQueen;
        this.playerColor = playerColor;
        this.enPassantMove = enPassant;
        this.doublePawnMove = doublePawnMove;

    }

    public boolean isDoublePawnMove() {
        return doublePawnMove;
    }

    public boolean isEnPassantMove() {
        return enPassantMove;
    }

    public void setEnPassantMove(boolean enPassantMove) {
        this.enPassantMove = enPassantMove;
    }

    public void setCaputreMove(String input) {
        caputreMove = true;
        caputrePiece = input;
    }

    public void clearCaputreMove() {
        caputreMove = false;
        caputrePiece = "";
    }

    public boolean isCaputreMove() {
        return caputreMove;
    }

    public String getCaputrePiece() {
        return caputrePiece;
    }

    /**
     * calls <h1>getFromFile()</h1>
     *
     * @return
     */
    public int gff() {
        return getFromFile();
    }

    public int getFromFile() {
        return fromFile;
    }

    /**
     * calls <h1>getToFile()</h1>
     *
     * @return
     */
    public int gtf() {
        return getToFile();
    }

    public int getToFile() {
        return toFile;
    }

    /**
     * calls <h1>getFromRank()</h1>
     *
     * @return
     */
    public int gfr() {
        return getFromRank();
    }

    public int getFromRank() {
        return fromRank;
    }

    /**
     * calls <h1>getToRank()</h1>
     *
     * @return
     */
    public int gtr() {
        return getToRank();
    }

    public int getToRank() {
        return toRank;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public boolean isCastleWhiteKing() {
        return castleWhiteKing;
    }

    public boolean isCastleBlackKing() {
        return castleBlackKing;
    }

    public boolean isCastleWhiteQueen() {
        return castleWhiteQueen;
    }

    public boolean isCastleBlackQueen() {
        return castleBlackQueen;
    }

    public boolean isPlayerColor() {
        return playerColor;
    }

    /**
     * @return true if one or more castle is set true return only false if all
     * castle is false
     */
    public boolean isCastling() {
        return castleBlackKing || castleBlackQueen || castleWhiteKing || castleWhiteQueen;
    }

    public char getType() {
        char value = ' ';
        if (caputrePiece == null) {
            return value;
        }

        switch (caputrePiece) {
            case "Pawn":
                value = 'P';
                break;
            case "Rook":
                value = 'R';
                break;
            case "Knight":
                value = 'N';
                break;
            case "Bishop":
                value = 'B';
                break;
            case "Queen":
                value = 'Q';
                break;
            case "King":
                value = 'K';
                break;
            default:
                break;
        }

        if (playerColor == true) { //white side
            value = Character.toUpperCase(value);
        } else { // black side
            value = Character.toLowerCase(value);
        }
        return value;
    }

    @Override
    public String toString() {
        String cwk = castleWhiteKing ? "K" : "-";
        String cwq = castleWhiteQueen ? "Q" : "-";
        String cbk = castleBlackKing ? "k" : "-";
        String cbq = castleBlackQueen ? "q" : "-";
        String castling = cwk + cwq + cbk + cbq;
        return "Move " + getMoveString() + "  {(" + fromRank + "," + fromFile + ") to (" + toRank + "," + toFile + ") prom=" + (promoted ? "1" : "0") + " castling: " + castling + " player=" + (playerColor ? "White" : "Black") + ", Dobble Pawn=" + (doublePawnMove ? "1":"0") + ", enPassant=" + (isEnPassantMove()? "1":"0") + "}";
    }

    public String getMoveString() {

        //RANK
        char[] ranks = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int c1 = (getFromRank() + 1);
        char c2 = ranks[getFromFile()];
        int c3 = (getToRank() + 1);
        char c4 = ranks[getToFile()];

        return c2 + "" + c1 + "" + c4 + "" + c3;
    }

    public boolean compareMoveTo(Move i) {
        return !(i.gtf() != gtf() || i.gtr() != gtr());
    }

    public boolean compareMove(Move i) {
        boolean t0 = (i.caputreMove == caputreMove);
        boolean t1 = (i.castleBlackKing == castleBlackKing);
        boolean t2 = (i.castleWhiteKing == castleWhiteKing);
        boolean t3 = (i.castleBlackQueen == castleBlackQueen);
        boolean t4 = (i.castleWhiteQueen == castleWhiteQueen);
        boolean t5 = (i.playerColor == playerColor);
        boolean t6 = (i.promoted == promoted);
        boolean t7 = (i.gtf() == gtf() || i.gtr() == gtr() || i.gff() == gff() || i.gfr() == gfr());
        return t0 && t1 && t2 && t3 && t4 && t5 && t6 && t7;
    }
}
