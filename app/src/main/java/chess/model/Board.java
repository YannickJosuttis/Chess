package chess.model;

import chess.model.piece.utils.PieceUtils;

/**
 * Board
 */
public class Board {

    public static final int NUMBER_OF_FILES = 8;
    public static final int NUMBER_OF_RANKS = 8;

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
        return squares[file][rank];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Board other) {
            for (int rank = 0; rank < NUMBER_OF_RANKS; rank++) {
                for (int file = 0; file < NUMBER_OF_FILES; file++) {
                    if (!this.squares[file][rank].equals(other.squares[file][rank])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_RANKS; i++) {
            for (int j = 0; j < NUMBER_OF_FILES; j++) {
                sb.append(squares[j][i].getPiece() == null ? "-" : PieceUtils.toChar(squares[j][i].getPiece()));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}