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
 * @description handle all the player information
 * @date
 */
public class Player {

    private final String nameWhite;
    private final String nameBlack;
    private boolean color;

    /**
     * @param color (true = wite side) (false = black side)
     */
    public Player(boolean color) {
        this.color = color;
        this.nameBlack = "Black";
        this.nameWhite = "White";
    }

    public String getName() {
        return color ? nameWhite : nameBlack;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
