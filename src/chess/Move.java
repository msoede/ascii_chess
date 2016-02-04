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

    public Move(int fromRank, int fromFile, int toRank, int toFile, boolean promoted, boolean castleWhiteKing, boolean castleBlackKing, boolean castleWhiteQueen, boolean castleBlackQueen, boolean playerColor) {
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
    }
    
    

    public int gff() {
        return getFromFile();
    }

    public int getFromFile() {
        return fromFile;
    }

    public int gtf() {
        return getToFile();
    }

    public int getToFile() {
        return toFile;
    }

    public int gfr() {
        return getFromRank();
    }

    public int getFromRank() {
        return fromRank;
    }

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

    @Override
    public String toString() {
        String cwk = castleWhiteKing ? "K" : "-";
        String cwq = castleWhiteQueen ? "Q" : "-";
        String cbk = castleBlackKing ? "k" : "-";
        String cbq = castleBlackQueen ? "q" : "-";
        String castling = cwk + cwq + cbk + cbq;

        return "Move{(" + fromRank + "," + fromFile + ") to (" + toRank + "," + toFile + ") prom=" + (promoted ? "1" : "0")+" castling: " + castling + " playerColor=" + (playerColor?"White":"Black") + '}';
    }
}
