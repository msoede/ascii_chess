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

import chess.Objects.Fen;
import chess.Objects.Board;
import chess.Objects.Move;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        Board board = new Board();
        board.setStartPosistion();
        Evaluation evaluation = new Evaluation();
        MoveGen moveGen = new MoveGen();
        Prints prints = new Prints();
        Main main = new Main();
        AlfaBetaSearch alfaBetaSearch = new AlfaBetaSearch(evaluation);

        //print welcome messages
        prints.printWelcomeMessage();
        prints.handleArguments(board, args);

        String input;
        while (true) {
            board.printBoard();
            if (board.isSide() == board.isHumanPlayer()) {
                System.out.println("Enter to the next move: (" + board.getCurrentPlayer().getName() + ")");
                input = main.getInputFromUser();
                while (main.getCommandToDo(input, board) == false) {
                    System.out.println("invalid move(" + input + ") string, enter new move:");
                    input = main.getInputFromUser();
                }
                if (main.validateMoveString(input)) { // make sure it move and not a command
                    //after her the move string is valid
                    if (moveGen.validateMoveAndDoTheMove(input, board)) {
                        System.out.println("Move valid now it is the other player");
                    }
                }
            } else { // computer move
                alfaBetaSearch.FindBedstMove(board);
            }
        }
    }

    public String getInputFromUser() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = br.readLine();
        } catch (IOException ex) {
            System.out.println("Error with reading input from keyboard");
        }
        return input.toLowerCase();
    }

    public boolean getCommandToDo(String input, Board board) {
        switch (input) {
            case "q":
                System.out.println("quit program!");
                System.out.println("have a nice day!");
                System.exit(0);
            case "h":
                System.out.println("+------+---------------------+");
                System.out.println("| key  | info                |");
                System.out.println("+------+---------------------+");
                System.out.println("| h    | help                |");
                System.out.println("| q    | quite               |");
                System.out.println("| fen  | get fen string      |");
                System.out.println("| undo | undo last move      |");
                System.out.println("| move | find next best move |");
                System.out.println("+------+---------------------+");
                return true;
            case "fen":
                Fen fen = new Fen();
                System.out.println("+------------+---------------------------------------------------------------+");
                System.out.format("| Fen String | %61s |\n", fen.getFen(board));
                System.out.println("+------------+---------------------------------------------------------------+");
                return true;
            case "undo":
                System.out.println("Undo move");
                board.undoLastMove();
                board.printBoard();
                return true;
            case "move":
                System.out.println("Find bedst move");
                System.out.println("Not implemnted YET!!");
                return true;
            case "list":
                System.out.println("Movelist");
                System.out.println(board.getMOveHistoryString());
                return true;
            case "movelist":
                MoveGen moveGen = new MoveGen();
                ArrayList<Move> moveList = moveGen.generateAllMoves(board);
                System.out.println("Movelist");
                int i = 1;
                for (Move childMove : moveList) {
                    System.out.format("%03d : %s\n", i, childMove.getMoveString());
                    i++;
                }
                return true;
            default:
                return validateMoveString(input);
        }
    }

    /**
     *
     * @param input
     * @return true if the string is a valid move string return false if the
     * string is not a valid move string
     */
    public boolean validateMoveString(String input) {

        if (input.length() != 4 || input.length() <= 3) {
            return false; // make sure the lenth is 4 letter long
        }
        input = input.toLowerCase();

        char fromRank = input.charAt(0);
        char fromFile = input.charAt(1);
        char toRank = input.charAt(2);
        char toFile = input.charAt(3);

        if (validateRankChar(fromRank) == false || validateRankChar(toRank) == false) {
            return false; // make sure the from rank is a valid letter
        }

        if (validateFileChar(fromFile) == false || validateFileChar(toFile) == false) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param input
     * @return true if the char is an letter from a to h
     * @return false if the char is NOT an letter from a to h
     */
    private boolean validateRankChar(char input) {
        char[] letter = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int i = 0; i < letter.length; i++) {
            if (letter[i] == input) {
                return true;
            }
        }
        return false;
    }

    private boolean validateFileChar(char input) {
        char[] letter = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};
        for (int i = 0; i < letter.length; i++) {
            if (letter[i] == input) {
                return true;
            }
        }
        return false;
    }
}
