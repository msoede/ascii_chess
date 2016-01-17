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
public class Validate {

    /**
     *
     * @param input
     * @return true if the string is a valid move string return false if the
     * string is not a valid move string
     */
    public boolean validateMoveString(String input) {
        if (input.length() != 4) {
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

    /**
     *
     * @param move format: "a2a3"
     * @param board
     * @paentPlayer
     * @return
     */
    public boolean validateMoveAndDoTheMove(String move, Board board) {

        boolean currentPlayer = board.getSide();
        int fromFile = 0;
        int fromRank = move.charAt(1) - '1';
        int toFile = 0;
        int toRank = move.charAt(3) - '1';

        for (int i = 0; i < 8; i++) {
            if (move.charAt(0) == ('a' + i)) {
                fromFile = i;
            }
            if (move.charAt(2) == ('a' + i)) {
                toFile = i;
            }
        }

        int n1 = fromRank;
        int l1 = fromFile;
        int n2 = toRank;
        int l2 = toFile;
        Move moveTomake = new Move(n1, l1, n2, l2, false, false, false, false, false, false);

        if (board.getPiece(fromRank, fromFile) == null) {
            System.out.println("There's no piece at the specified location");
            return false;
        }

        if (board.getPiece(fromRank, fromFile) != null && currentPlayer != board.getPiece(fromRank, fromFile).getPlayer().isColor()) {
            System.out.println("That's not your piece, bro.");
            return false;
        }

        if (board.getPiece(toRank, toFile) != null && currentPlayer == board.getPiece(toRank, toFile).getPlayer().isColor()) {
            System.out.println("You already have a piece there.");
            return false;
        }

        int rowDiff = fromRank - toRank;
        int colDiff = fromFile - toFile;
        int absRowDiff = Math.abs(rowDiff);
        int absColDiff = Math.abs(colDiff);

        System.out.println("move(" + move + "): from(" + fromRank + ":" + fromFile + ")   to(" + toRank + ":" + toFile + ")");
        System.out.println("row diff: (" + rowDiff + ")    colDiff: (" + colDiff + ")");

        //WHITE PAWN
        if (board.getPiece(n1, l1).getName().equals("Pawn") && currentPlayer) {
            if (n1 != 1 && rowDiff <= -2) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (n1 == 1 && rowDiff < -2) {
                System.out.println("Pawns may move 2 spaces forward their first move.");
                return false;
            } else if (rowDiff >= 0) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (Math.abs(colDiff) > 1) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (Math.abs(colDiff) == 1 && rowDiff == -1 && board.getPiece(n2, l2) == null) {
                System.out.println("There must be an enemy piece at " + l2 + "," + n2 + " for you to move there.");
                return false;
            } else if (rowDiff == -1 && colDiff == 0 && board.getPiece(n2, l2) != null && board.getPiece(n2, l2).getPlayer().isColor() == !currentPlayer) {
                System.out.println("An enemy piece is blocking your move!");
                return false;
            }
            return board.makeMove(moveTomake);
        } //PAWN BLACK
        else if (board.getPiece(n1, l1).getName().equals("Pawn") && !currentPlayer) {
            if (n1 != 6 && rowDiff >= 2) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (n1 == 6 && rowDiff > 2) {
                System.out.println("Pawns may move 2 spaces forward their first move.");
                return false;
            } else if (rowDiff <= 0) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (Math.abs(colDiff) > 1) {
                System.out.println("Pawns can't move like that.");
                return false;

            } else if (Math.abs(colDiff) == 1 && rowDiff == 1 && board.getPiece(n2, l2) == null) {
                System.out.println("There must be an enemy piece at " + l2 + "," + n2 + " for you to move there.");
                return false;

            } else if (rowDiff == 1 && colDiff == 0 && board.getPiece(n2, l2) != null && board.getPiece(n2, l2).getPlayer().isColor() == !currentPlayer) {
                System.out.println("An enemy piece is blocking your move!");
                return false;
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(n1, l1).getName().equals("King")) {
            if (Math.abs(rowDiff) > 1 || Math.abs(colDiff) > 1) {
                System.out.println("Kings may only move one space at a time.");
                return false;
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(n1, l1).getName().equals("Queen")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff) && rowDiff != 0 && colDiff != 0) {
                System.out.println("Queens can do a lot of things, but they can't apparate.");
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                for (int i = n1 - 1; i > n2; i--) {
                    for (int j = l1 - 1; j > l2; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                for (int i = n1 + 1; i < n2; i++) {
                    for (int j = l1 + 1; j < l2; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                for (int i = n1 - 1; i > n2; i--) {
                    for (int j = l1 + 1; j < l2; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                for (int i = n1 + 1; i < n2; i++) {
                    for (int j = l1 - 1; j > l2; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff == 0 && colDiff > 0) {
                for (int i = l1 - 1; i > l2; i--) {
                    if (board.getPiece(n1, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = l1 + 1; i < l2; i++) {
                    if (board.getPiece(n1, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = n1 - 1; i > n2; i--) {
                    if (board.getPiece(i, l1) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = n1 + 1; i < n2; i++) {
                    if (board.getPiece(i, l1) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(n1, l1).getName().equals("Knight")) {
            if (Math.abs(rowDiff) * Math.abs(colDiff) != 2) {
                System.out.println("Knights can't move like that.");
                return false;
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(n1, l1).getName().equals("Bishop")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff)) {
                System.out.println("Bishops can't move like that.");
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                for (int i = n1 - 1; i > n2; i--) {
                    for (int j = l1 - 1; j > l2; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                for (int i = n1 + 1; i < n2; i++) {
                    for (int j = l1 + 1; j < l2; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                for (int i = n1 - 1; i > n2; i--) {
                    for (int j = l1 + 1; j < l2; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                for (int i = n1 + 1; i < n2; i++) {
                    for (int j = l1 - 1; j > l2; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(n1, l1).getName().equals("Rook")) {
            if (rowDiff != 0 && colDiff != 0) {
                System.out.println("Rooks can't move like that.");
                return false;
            }

            if (rowDiff == 0 && colDiff > 0) {
                for (int i = l1 - 1; i > l2; i--) {
                    if (board.getPiece(n1, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = l1 + 1; i < l2; i++) {
                    if (board.getPiece(n1, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = n1 - 1; i > n2; i--) {
                    if (board.getPiece(i, l1) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = n1 + 1; i < n2; i++) {
                    if (board.getPiece(i, l1) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            }
            return board.makeMove(moveTomake);
        }
        return false; // default value
    }

    public boolean validateMove(Move moveTomake, Board board) {

        boolean currentPlayer = board.getSide();

        int fromRank = moveTomake.getFromRank();
        int fromFile = moveTomake.getFromFile();
        int toRank = moveTomake.getToRank();
        int toFile = moveTomake.getToFile();


        if (board.getPiece(fromRank, fromFile) == null) {
            System.out.println("There's no piece at the specified location");
            return false;
        }

        if (board.getPiece(fromRank, fromFile) != null && currentPlayer != board.getPiece(fromRank, fromFile).getPlayer().isColor()) {
            System.out.println("That's not your piece, bro.");
            return false;
        }

        if (board.getPiece(toRank, toFile) != null && currentPlayer == board.getPiece(toRank, toFile).getPlayer().isColor()) {
            System.out.println("You already have a piece there.");
            return false;
        }

        int rowDiff = fromRank - toRank;
        int colDiff = fromFile - toFile;
        int absRowDiff = Math.abs(rowDiff);
        int absColDiff = Math.abs(colDiff);


        //WHITE PAWN
        if (board.getPiece(fromRank, fromFile).getName().equals("Pawn") && currentPlayer) {
            if (fromRank != 1 && rowDiff <= -2) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (fromRank == 1 && rowDiff < -2) {
                System.out.println("Pawns may move 2 spaces forward their first move.");
                return false;
            } else if (rowDiff >= 0) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (Math.abs(colDiff) > 1) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (Math.abs(colDiff) == 1 && rowDiff == -1 && board.getPiece(toRank, toFile) == null) {
                System.out.println("There must be an enemy piece at " + toFile + "," + toRank + " for you to move there.");
                return false;
            } else if (rowDiff == -1 && colDiff == 0 && board.getPiece(toRank, toFile) != null && board.getPiece(toRank, toFile).getPlayer().isColor() == !currentPlayer) {
                System.out.println("An enemy piece is blocking your move!");
                return false;
            }
            return board.makeMove(moveTomake);
        } //PAWN BLACK
        else if (board.getPiece(fromRank, fromFile).getName().equals("Pawn") && !currentPlayer) {
            if (fromRank != 6 && rowDiff >= 2) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (fromRank == 6 && rowDiff > 2) {
                System.out.println("Pawns may move 2 spaces forward their first move.");
                return false;
            } else if (rowDiff <= 0) {
                System.out.println("Pawns can't move like that.");
                return false;
            } else if (Math.abs(colDiff) > 1) {
                System.out.println("Pawns can't move like that.");
                return false;

            } else if (Math.abs(colDiff) == 1 && rowDiff == 1 && board.getPiece(toRank, toFile) == null) {
                System.out.println("There must be an enemy piece at " + toFile + "," + toRank + " for you to move there.");
                return false;

            } else if (rowDiff == 1 && colDiff == 0 && board.getPiece(toRank, toFile) != null && board.getPiece(toRank, toFile).getPlayer().isColor() == !currentPlayer) {
                System.out.println("An enemy piece is blocking your move!");
                return false;
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(fromRank, fromFile).getName().equals("King")) {
            if (Math.abs(rowDiff) > 1 || Math.abs(colDiff) > 1) {
                System.out.println("Kings may only move one space at a time.");
                return false;
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Queen")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff) && rowDiff != 0 && colDiff != 0) {
                System.out.println("Queens can do a lot of things, but they can't apparate.");
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff == 0 && colDiff > 0) {
                for (int i = fromFile - 1; i > toFile; i--) {
                    if (board.getPiece(fromRank, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = fromFile + 1; i < toFile; i++) {
                    if (board.getPiece(fromRank, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    if (board.getPiece(i, fromFile) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    if (board.getPiece(i, fromFile) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Knight")) {
            if (Math.abs(rowDiff) * Math.abs(colDiff) != 2) {
                System.out.println("Knights can't move like that.");
                return false;
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Bishop")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff)) {
                System.out.println("Bishops can't move like that.");
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            System.out.println("There's a piece blocking your move!");
                            return false;
                        }
                    }
                }
            }
            return board.makeMove(moveTomake);
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Rook")) {
            if (rowDiff != 0 && colDiff != 0) {
                System.out.println("Rooks can't move like that.");
                return false;
            }

            if (rowDiff == 0 && colDiff > 0) {
                for (int i = fromFile - 1; i > toFile; i--) {
                    if (board.getPiece(fromRank, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = fromFile + 1; i < toFile; i++) {
                    if (board.getPiece(fromRank, i) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    if (board.getPiece(i, fromFile) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    if (board.getPiece(i, fromFile) != null) {
                        System.out.println("There's a piece blocking your move!");
                        return false;
                    }
                }
            }
            return board.makeMove(moveTomake);
        }
        return false; // default value
    }
}
