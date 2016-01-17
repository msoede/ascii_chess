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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Definetions 
        Board board = new Board("White", "Black");
        Validate validate = new Validate();
        Evaluation evaluation = new Evaluation();
        Prints prints = new Prints();
        Main main = new Main();
        MoveGen moveGen = new MoveGen();

        //print welcome messages
        prints.printWelcomeMessage();
        prints.handleArguments(board, args);

        System.out.println("evo board: " + evaluation.evaluateBoard(board));
        String input;
        while (true) {
            board.printBoard();
            moveGen.generateAllPawnMoves(board);

            System.out.println("Enter to the next move: (" + board.getCurrentPlayer().getName() + ")");
            input = main.getInputFromUser(board);

            while (validate.validateMoveString(input) == false) {
                System.out.println("invalid move(" + input + ") string, enter new move:");
                input = main.getInputFromUser(board);
            }
            if (validate.validateMoveAndDoTheMove(input, board)) {
                board.switchSide();
            }
            System.out.println("Evo: " + evaluation.evaluateBoard(board));
        }
    }

    public String getInputFromUser(Board board) {
        String input = System.console().readLine();
        input = input.toLowerCase();
        if (input.equals("q")) {
            System.out.println("quit program!");
            System.out.println("have a nice day!");
            System.exit(0);
        } else if (input.equals("h")) {
            System.out.println("+-----+----------------+");
            System.out.println("| key | info           |");
            System.out.println("+-----+----------------+");
            System.out.println("| h   | help           |");
            System.out.println("| q   | quite          |");
            System.out.println("| fen | get fen string |");
            System.out.println("+-----+----------------+");
        } else if (input.equals("fen")) {
            Fen fen = new Fen();
            System.out.println("+------------+---------------------------------------------------------------+");
            System.out.format("| Fen String | %61s |\n", fen.getFen(board));
            System.out.println("+------------+---------------------------------------------------------------+");
        }
        return input;
    }
}
