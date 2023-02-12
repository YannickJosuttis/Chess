package chess.model.board;

import java.util.Objects;

import chess.model.piece.Piece;

public class Square {

    private Piece piece;

    public void removePiece() {
        piece = null;
    }

    public void setPiece(Piece piece) {
        Objects.requireNonNull(piece);
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isOccupied() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return Objects.isNull(piece);
    }

}