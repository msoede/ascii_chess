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
 * @description handle the piece information there will be 64 piece to reprecent
 * a hole board
 * @date
 */
public class Piece {

    private String name;
    private Player player;

    //+--------+--------+
    //|  Name  |  Type  |
    //+--------+--------+
    //| Pawn   | P or p |
    //| Rook   | R or r |
    //| Knight | N or n |
    //| Bishop | B or b |
    //| Queen  | Q or q |
    //| King   | K or k |
    //+--------+--------+
    public Piece(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public Piece(char type, Player player) {
        type = Character.toUpperCase(type);

        String value = " "; // defualt value
        switch (type) {
            case 'P':
                value = "Pawn";
                break;
            case 'R':
                value = "Rook";
                break;
            case 'N':
                value = "Knight";
                break;
            case 'B':
                value = "Bishop";
                break;
            case 'Q':
                value = "Queen";
                break;
            case 'K':
                value = "King";
                break;
            default:
                break;
        }
        this.name = value;
        this.player = player;
    }

    public char getType() {
        char value = ' '; // defualt value
        switch (name) {
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
        if (player.isColor() == true) { //white side
            value = Character.toUpperCase(value);
        } else { // black side
            value = Character.toLowerCase(value);
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
    
    public boolean getPlayerColor(){
        return player.isColor();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getColorString() {
        return player != null ? player.getName() : "";
    }
}
