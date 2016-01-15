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
 * @description handle the piece information there will be 64 piece to reprecent a hole board
 * @date 
 */
public class Piece {

    private String name;
    private Player player;

    public Piece(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public String getType() {
        String value = " "; // defualt value
        switch (name) {
            case "Pawn":
                value = "P";
                break;
            case "Rook":
                value = "R";
                break;
            case "Knight":
                value = "N";
                break;
            case "Bishop":
                value = "B";
                break;
            case "Queen":
                value = "Q";
                break;
            case "King":
                value = "K";
                break;
            default:
                break;
        }

        if (player.isColor() == true) { //white side
            value = value.toLowerCase();
        } else { // black side
            value = value.toUpperCase();
        }

        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getColorString() {
        if (player != null) {
            if (player.isColor()) {
                return "White";
            } else {
                return "Black";
            }
        }
        return "";
    }
}
