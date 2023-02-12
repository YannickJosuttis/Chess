package chess.model.board;

import chess.model.move.Move;
import chess.model.piece.Color;

public class Position {

    // piece placement data
    private Board board;
    // active color
    private Color activeColor;
    // castling availability
    private CastlingAvailability castlingAvailability;
    // en passant target Square
    private Square enPassantTargetSquare;
    // halfmove clock
    private int halfmoveClock;
    // fullmove number
    private int fullmoveNumber;

    public Position(Board board, Color activeColor, CastlingAvailability castlingAvailability,
            Square enPassantTargetSquare, int halfmoveClock, int fullmoveNumber) {
        this.board = board;
        this.activeColor = activeColor;
        this.castlingAvailability = castlingAvailability;
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.halfmoveClock = halfmoveClock;
        this.fullmoveNumber = fullmoveNumber;
    }

    public void move(Move move) {
        move.execute();
        /* move
         * capture
         * enpassant
         * castle
         * promotion
         * promotion capture
         */
    }

    public Board getBoard() {
        return board;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public Square getEnPassantTargetSquare() {
        return enPassantTargetSquare;
    }
    
    public CastlingAvailability getCastlingAvailability() {
        return castlingAvailability;
    }

    public int getHalfmoveClock() {
        return halfmoveClock;
    }

    public int getFullmoveNumber() {
        return fullmoveNumber;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    public void setEnPassantTargetSquare(Square enPassantTargetSquare) {
        this.enPassantTargetSquare = enPassantTargetSquare;
    }

    public void setCastlingAvailability(CastlingAvailability castlingAvailability) {
        this.castlingAvailability = castlingAvailability;
    }

    public void setHalfmoveClock(int halfmoveClock) {
        this.halfmoveClock = halfmoveClock;
    }
    
    public void setFullmoveNumber(int fullmoveNumber) {
        this.fullmoveNumber = fullmoveNumber;
    }

}