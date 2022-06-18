package chess.model;

/**
 * Board
 */
public class Board {

    private static final int NUMBER_OF_FILES = 8;
    private static final int NUMBER_OF_RANKS = 8;

    private Square[][] squares;

    public Board() {
        squares = new Square[NUMBER_OF_FILES][NUMBER_OF_RANKS];
        fill();
    }

    private void fill() {
        for (int rank = 0; rank < NUMBER_OF_RANKS; rank++) {
            for (int file = 0; file < NUMBER_OF_FILES; file++) {
                squares[file][rank] = new Square(file, rank);
            }
        }
    }

    public void clear() {
        for (int rank = 0; rank < NUMBER_OF_RANKS; rank++) {
            for (int file = 0; file < NUMBER_OF_FILES; file++) {
                squares[file][rank].removePiece();
            }
        }
    }

    public boolean isInBorder(int file, int rank) {
        return file >= 0 && file < NUMBER_OF_FILES && rank >= 0 && rank < NUMBER_OF_RANKS;
    }

    public Square getSquare(int file, int rank) {
        if (isInBorder(file, rank)) {
            return squares[file][rank];
        }
        throw new IllegalArgumentException("Invalid file or rank");
    }

}