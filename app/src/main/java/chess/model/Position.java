package chess.model;

import chess.model.piece.properties.Color;
import chess.model.piece.utils.PieceUtils;

public class Position {

    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private final Board board;
    private Color activeColor;
    private boolean[] castlingAvailability;
    private Square enPassantTarget;
    private int halfMoveClock;
    private int moveNumber;

    public Position(Board board) {
        this.board = board;
    }

    public void clear() {
        board.clear();
        this.activeColor = Color.WHITE;
        this.castlingAvailability = new boolean[4];
        this.enPassantTarget = null;
        this.halfMoveClock = 0;
        this.moveNumber = 1;
    }

    public void fromFEN(String fen) {
        clear();
        String[] parts = fen.split(" ");
        setPiecePlacement(parts[0]);
        setActiveColor(parts[1]);
        setCastlingAvailability(parts[2]);
        setEnPassantTarget(parts[3]);
        setHalfMoveClock(parts[4]);
        setMoveNumber(parts[5]);
    }

    private void setPiecePlacement(String string) {
        int file = 0;
        int rank = 0;
        for (char c : string.toCharArray()) {
            if (c == '/') {
                file = 0;
                rank++;
            } else if (Character.isDigit(c)) {
                file += c - '0';
            } else {
                board.getSquare(file, rank).setPiece(PieceUtils.fromChar(c));
                file++;
            }
        }
    }

    private void setActiveColor(String string) {
        if (string.equals("w")) {
            activeColor = Color.WHITE;
        } else if (string.equals("b")) {
            activeColor = Color.BLACK;
        } else {
            throw new IllegalArgumentException("Invalid active color: " + string);
        }
    }

    private void setCastlingAvailability(String string) {
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == 'K') {
                castlingAvailability[0] = true;
            } else if (c == 'Q') {
                castlingAvailability[1] = true;
            } else if (c == 'k') {
                castlingAvailability[2] = true;
            } else if (c == 'q') {
                castlingAvailability[3] = true;
            }
        }
    }

    private void setEnPassantTarget(String string) {
        if (string.equals("-")) {
            enPassantTarget = null;
        } else {
            enPassantTarget = board.getSquare(string.charAt(0) - 'a', string.charAt(1) - '0' - 1);
        }
    }

    private void setHalfMoveClock(String string) {
        halfMoveClock = Integer.parseInt(string);
    }

    private void setMoveNumber(String string) {
        moveNumber = Integer.parseInt(string);
    }

    public String toFEN() {
        StringBuilder sb = new StringBuilder();
        for (int rank = 0; rank < 8; rank++) {
            int empty = 0;
            for (int file = 0; file < 8; file++) {
                Square square = board.getSquare(file, rank);
                if (square.getPiece() == null) {
                    empty++;
                } else {
                    if (empty > 0) {
                        sb.append(empty);
                        empty = 0;
                    }
                    sb.append(PieceUtils.toChar(board.getSquare(file, rank).getPiece()));
                }
            }
            if (empty > 0) {
                sb.append(empty);
            }
            if (rank < 7) {
                sb.append("/");
            }
        }
        sb.append(" ");
        sb.append(activeColor == Color.WHITE ? "w" : "b");
        sb.append(" ");
        sb.append(castlingAvailability[0] ? "K" : "");
        sb.append(castlingAvailability[1] ? "Q" : "");
        sb.append(castlingAvailability[2] ? "k" : "");
        sb.append(castlingAvailability[3] ? "q" : "");
        if (!castlingAvailability[0] && !castlingAvailability[1] && !castlingAvailability[2]
                && !castlingAvailability[3]) {
            sb.append("-");
        }
        sb.append(" ");
        if (enPassantTarget == null) {
            sb.append("-");
        } else {
            sb.append(enPassantTarget.getFile() + 1 + "" + enPassantTarget.getRank());
        }
        sb.append(" ");
        sb.append(halfMoveClock);
        sb.append(" ");
        sb.append(moveNumber);
        return sb.toString();
    }

    public Board getBoard() {
        return board;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public boolean isWhiteKingsideCastleAvailable() {
        return castlingAvailability[0];
    }

    public boolean isWhiteQueensideCastleAvailable() {
        return castlingAvailability[1];
    }

    public boolean isBlackKingsideCastleAvailable() {
        return castlingAvailability[2];
    }

    public boolean isBlackQueensideCastleAvailable() {
        return castlingAvailability[3];
    }

    public Square getEnPassantTarget() {
        return enPassantTarget;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    public void setWhiteKingsideCastleAvailability(boolean isWhiteKingsideCastleAvailable) {
        castlingAvailability[0] = isWhiteKingsideCastleAvailable;
    }

    public void setWhiteQueensideCastleAvailability(boolean isWhiteQueensideCastleAvailable) {
        castlingAvailability[1] = isWhiteQueensideCastleAvailable;
    }

    public void setBlackKingsideCastleAvailability(boolean isBlackKingsideCastleAvailable) {
        castlingAvailability[2] = isBlackKingsideCastleAvailable;
    }

    public void setBlackQueensideCastleAvailability(boolean isBlackQueensideCastleAvailable) {
        castlingAvailability[3] = isBlackQueensideCastleAvailable;
    }

    public void setEnPassantTarget(Square square) {
        this.enPassantTarget = square;
    }

    public void setHalfMoveClock(int halfMoveClock) {
        this.halfMoveClock = halfMoveClock;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String toString() {
        return toFEN();
    }
}
