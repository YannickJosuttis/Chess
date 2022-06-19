package chess.model;

import chess.model.piece.IPiece;

/**
 * Square
 */
public class Square {

    private int file;
    private int rank;
    private IPiece piece;

    public Square(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }
    
    public boolean isOccupied() {
        return piece != null;
    }

    public IPiece getPiece() {
        return piece;
    }

    public void setPiece(IPiece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        piece = null;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (obj instanceof Square other) {
            return this.file == other.file && this.rank == other.rank && (this.piece == null && other.piece == null || this.piece.equals(other.piece));
        }
        return false;
    }
}