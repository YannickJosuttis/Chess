package chess.model.board;

public class Board {

    public static final int NUMBER_OF_FILES = 8;
    public static final int NUMBER_OF_RANKS = 8;

    private Square[][] squares;

    public Board() {
        squares = new Square[NUMBER_OF_FILES][NUMBER_OF_RANKS];
        for (int file = 0; file < NUMBER_OF_FILES; file++) {
            for (int rank = 0; rank < NUMBER_OF_RANKS; rank++) {
                squares[file][rank] = new Square();
            }
        }
    }

    public Square getSquare(int file, int rank) {
        return squares[file][rank];
    }

}
