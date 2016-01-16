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
 * @description Handle many of the printouts used when the program starts up
 * @date
 */
public class Prints {

    /**
     * prints welcome message ascii art are generated from
     * http://patorjk.com/software/taag/#p=display&f=Big&t=msoede%0A%0AChess
     * font type: Big prints info about the developer
     */
    public void printWelcomeMessage() {
        System.out.println("                               _      ");
        System.out.println("                              | |     ");
        System.out.println("  _ __ ___  ___  ___   ___  __| | ___ ");
        System.out.println(" | '_ ` _ \\/ __|/ _ \\ / _ \\/ _` |/ _ \\");
        System.out.println(" | | | | | \\__ \\ (_) |  __/ (_| |  __/");
        System.out.println(" |_|_|_| |_|___/\\___/ \\___|\\__,_|\\___|");
        System.out.println("  / ____| |                           ");
        System.out.println(" | |    | |__   ___  ___ ___          ");
        System.out.println(" | |    | '_ \\ / _ \\/ __/ __|         ");
        System.out.println(" | |____| | | |  __/\\__ \\__ \\         ");
        System.out.println("  \\_____|_| |_|\\___||___/___/         ");
        System.out.println("");
        System.out.println("Program made by Mikkel Soede");
        System.out.println("");
    }

    /**
     * handle the argument from the command line h: help prints info about the
     * program and show possible arguments
     *
     * @param board
     * @param args
     */
    public void handleArguments(Board board, String[] args) {
        Fen fen = new Fen();

        if (args.length >= 1) {
            for (String tempArg : args) {
                if (tempArg.length() > 20) {
                    if (tempArg.substring(0, 3).equals("fen")) {
                        String fenString = tempArg.substring(3);
                        fen.loadFen(fenString, board);
                    }
                }
                tempArg = tempArg.toLowerCase();
                if (tempArg.equals("h")) {
                    printHelpInfo();
                    System.exit(0);
                } else if (tempArg.equals("b")) {
                    board.setHumanPlayer("black");
                } else if (tempArg.equals("w")) {
                    board.setHumanPlayer("white");
                } else if (tempArg.charAt(0) == 'd') {
                    board.setSearchDepth(Integer.parseInt(tempArg.replace("d", "")));
                } else if (tempArg.charAt(0) == 't') {
                    board.setSearchTime(Integer.parseInt(tempArg.replace("t", "")));
                }
            }
        }
        System.out.println("+-----------------------+-------------+--------------+-------------+");
        System.out.println("| Setup input arguments | Player side | Search depth | Search time |");
        System.out.println("+-----------------------+-------------+--------------+-------------+");
        System.out.format("| nr arg: %13d | %11s | %12d | %7d sec |\n", args.length, board.isHumanPlayer(), board.getSearchDepth(), board.getSearchTime());
        System.out.println("+-----------------------+-------------+--------------+-------------+");
    }

    /**
     * print help information
     */
    private void printHelpInfo() {
        System.out.println("+----------+------------------------------------------------------+------------------------------------------------------+\n"
                + "| argument |                         desc                         |                      how to use                      |\n"
                + "+----------+------------------------------------------------------+------------------------------------------------------+\n"
                + "| h        | help prints info about how to start the program      | java -jar chess.jar h                                |\n"
                + "| w        | set human player to white                            | java -jar chess.jar w                                |\n"
                + "| b        | set human player to black                            | java -jar chess.jar b                                |\n"
                + "| t        | set the time the computer player have to calculate   | java -jar chess.jar t15 //\"set time to 15 sec\"       |\n"
                + "| d        | set the search depth the alfa beta have to search in | java -jar chess.jar d6 //\"set the search depth to 6\" |\n"
                + "| q        | quits the program then it is running                 |                                                      |\n"
                + "+----------+------------------------------------------------------+------------------------------------------------------+");
    }

}
