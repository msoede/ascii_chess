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

        Move moveTomake = new Move(fromRank, fromFile, toRank, toFile, false, false, false, false, false, false);

        if (validateMove(moveTomake, board)) {
            board.makeMove(moveTomake);
            return true;
        }
        return false;
    }

    public boolean validateMove(Move moveTomake, Board board) {

        boolean currentPlayer = board.getSide();

        int fromRank = moveTomake.getFromRank();
        int fromFile = moveTomake.getFromFile();
        int toRank = moveTomake.getToRank();
        int toFile = moveTomake.getToFile();

        if (fromRank < 0 || fromRank >= 8 || fromFile < 0 || fromFile >= 8 || toRank < 0 || toRank >= 8 || toFile < 0 || toFile >= 8) {
            return false;
        }

        if (board.getPiece(fromRank, fromFile) == null) {
            return false;
        }

        if (board.getPiece(fromRank, fromFile) != null && currentPlayer != board.getPiece(fromRank, fromFile).getPlayer().isColor()) {
            return false;
        }

        if (board.getPiece(toRank, toFile) != null && currentPlayer == board.getPiece(toRank, toFile).getPlayer().isColor()) {
            return false;
        }

        int rowDiff = fromRank - toRank;
        int colDiff = fromFile - toFile;
        int absRowDiff = Math.abs(rowDiff);
        int absColDiff = Math.abs(colDiff);

        //WHITE PAWN
        if (board.getPiece(fromRank, fromFile).getName().equals("Pawn") && currentPlayer) {
            if (fromRank != 1 && rowDiff <= -2) {
                return false;
            } else if (fromRank == 1 && rowDiff < -2) {
                return false;
            } else if (rowDiff >= 0) {
                return false;
            } else if (Math.abs(colDiff) > 1) {
                return false;
            } else if (Math.abs(colDiff) == 1 && rowDiff == -1 && board.getPiece(toRank, toFile) == null) {
                return false;
            } else if (rowDiff == -1 && colDiff == 0 && board.getPiece(toRank, toFile) != null && board.getPiece(toRank, toFile).getPlayer().isColor() == !currentPlayer) {
                return false;
            }
            return true;
        } //PAWN BLACK
        else if (board.getPiece(fromRank, fromFile).getName().equals("Pawn") && !currentPlayer) {
            if (fromRank != 6 && rowDiff >= 2) {
                return false;
            } else if (fromRank == 6 && rowDiff > 2) {
                return false;
            } else if (rowDiff <= 0) {
                return false;
            } else if (Math.abs(colDiff) > 1) {
                return false;
            } else if (Math.abs(colDiff) == 1 && rowDiff == 1 && board.getPiece(toRank, toFile) == null) {
                return false;
            } else if (rowDiff == 1 && colDiff == 0 && board.getPiece(toRank, toFile) != null && board.getPiece(toRank, toFile).getPlayer().isColor() == !currentPlayer) {
                return false;
            }
            return true;
        } else if (board.getPiece(fromRank, fromFile).getName().equals("King")) {
            if (Math.abs(rowDiff) > 1 || Math.abs(colDiff) > 1) {
                return false;
            }
            return true;
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Queen")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff) && rowDiff != 0 && colDiff != 0) {
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    for (int j = fromFile + 1; j < toFile; j++) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    for (int j = fromFile - 1; j > toFile; j--) {
                        if (board.getPiece(i, j) != null) {
                            return false;
                        }
                    }
                }
            } else if (rowDiff == 0 && colDiff > 0) {
                for (int i = fromFile - 1; i > toFile; i--) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = fromFile + 1; i < toFile; i++) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            }
            return true;
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Knight")) {
            if (Math.abs(rowDiff) * Math.abs(colDiff) != 2) {
                return false;
            }
            return true;
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Bishop")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff)) {
                return false;
            }
            if (rowDiff > 0 && colDiff > 0) {
                int f = fromFile - 1;
                for (int r = fromRank - 1; r > toRank; r--) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f--;
                }
            } else if (rowDiff < 0 && colDiff < 0) {
                int f = fromFile + 1;
                for (int r = fromRank + 1; r < toRank; r++) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f++;
                }
            } else if (rowDiff > 0 && colDiff < 0) {
                int f = fromFile + 1;
                for (int r = fromRank - 1; r > toRank; r--) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f++;
                }
            } else if (rowDiff < 0 && colDiff > 0) {
                int f = fromFile - 1;
                for (int r = fromRank + 1; r < toRank; r++) {
                    if (board.getPiece(r, f) != null) {
                        return false;
                    }
                    f--;
                }
            }
            return true;
        } else if (board.getPiece(fromRank, fromFile).getName().equals("Rook")) {
            if (rowDiff != 0 && colDiff != 0) {
                return false;
            }

            if (rowDiff == 0 && colDiff > 0) {
                for (int i = fromFile - 1; i > toFile; i--) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff == 0 && colDiff < 0) {
                for (int i = fromFile + 1; i < toFile; i++) {
                    if (board.getPiece(fromRank, i) != null) {
                        return false;
                    }
                }
            } else if (rowDiff > 0 && colDiff == 0) {
                for (int i = fromRank - 1; i > toRank; i--) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            } else if (rowDiff < 0 && colDiff == 0) {
                for (int i = fromRank + 1; i < toRank; i++) {
                    if (board.getPiece(i, fromFile) != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false; // default value
    }
}
