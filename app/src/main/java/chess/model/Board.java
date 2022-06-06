package chess.model;

import java.util.Arrays;

import chess.model.piece.Piece;

public class Board {

    public static final int NUMBER_OF_FILES = 8;
    public static final int NUMBER_OF_RANKS = 8;
    private int[][] pieces;

    public Board() {
        this.pieces = new int[NUMBER_OF_FILES][NUMBER_OF_RANKS];
    }

    public void clear() {
        for (int file = 0; file < NUMBER_OF_FILES; file++) {
            for (int rank = 0; rank < NUMBER_OF_RANKS; rank++) {
                this.pieces[file][rank] = Piece.EMPTY;
            }
        }
    }

    public boolean isOccupied(int file, int rank) {
        return pieces[file][rank] != Piece.EMPTY;
    }

    public int getPieceAt(int file, int rank) {
        return pieces[file][rank];
    }

    public void setPieceAt(int piece, int file, int rank) {
        pieces[file][rank] = piece;
    }

    public void removePieceAt(int file, int rank) {
        pieces[file][rank] = Piece.EMPTY;
    }

    public void movePiece(int fromFile, int fromRank, int toFile, int toRank) {
        int piece = getPieceAt(fromFile, fromRank);
        removePieceAt(fromFile, fromRank);
        setPieceAt(piece, toFile, toRank);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(pieces);
    }

}
