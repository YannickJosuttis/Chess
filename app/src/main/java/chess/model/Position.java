package chess.model;

import chess.model.piece.Piece;
import chess.model.piece.PieceUtils;
import chess.model.piece.properties.Color;

/**
 * Class for representing a position on the chess board. A position is defined
 * by any information stored in the FEN (Forsyth-Edwards-Notation) notation. The
 * FEN notation consists of 6 fields:
 * <ul>
 * <li>Piece placement
 * <li>Active color
 * <li>Castling availability
 * <li>En passant target square
 * <li>Halfmove clock
 * <li>Fullmove number
 * <ul>
 */
public class Position {

    public static final String STARTING_POSITION_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private final Board board; // piece placement
    private int activeColor;
    private boolean[] castlingAvailability;
    private int enpassantFile, enpassantRank; // en passant target square
    private int halfmoveClock;
    private int fullmoveNumber;

    public Position() {
        this.board = new Board();
    }

    /**
     * Method that sets the position to the given FEN string.
     */
    public void fromFen(String fen) {

        validateFenSyntax(fen);
        

        String[] fields = fen.split(" ");
        setPiecePlacement(fields[0]);
        setActiveColor(fields[1]);
        setCastlingAvailability(fields[2]);
        setEnpassantTargetSquare(fields[3]);
        setHalfmoveClock(fields[4]);
        setFullmoveNumber(fields[5]);

    }

    private void setPiecePlacement(String string) {
        int file = 0;
        int rank = 0;
        for (char ch : string.toCharArray()) {
            if (ch == '/') {
                file = 0;
                rank++;
            } else if (Character.isDigit(ch)) {
                file += ch - '0';
            } else {
                board.setPieceAt(PieceUtils.fromChar(ch), file, rank);
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
        castlingAvailability = new boolean[4];
        for (char ch : string.toCharArray()) {
            switch (ch) {
                case 'K' -> castlingAvailability[0] = true;
                case 'Q' -> castlingAvailability[1] = true;
                case 'k' -> castlingAvailability[2] = true;
                case 'q' -> castlingAvailability[3] = true;
                case '-' -> {
                }
                default -> throw new IllegalArgumentException("Invalid castling availability: " + ch);
            }
        }
    }

    private void setEnpassantTargetSquare(String string) {
        if (string.equals("-")) {
            enpassantFile = -1;
            enpassantRank = -1;
        } else {
            enpassantFile = string.charAt(0) - 'a';
            enpassantRank = string.charAt(1) - '1';
        }
    }

    private void setHalfmoveClock(String string) {
        halfmoveClock = Integer.parseInt(string);
    }

    private void setFullmoveNumber(String string) {
        fullmoveNumber = Integer.parseInt(string);
    }

    /**
     * Method that returns the FEN string of the current position.
     */
    public String toFen() {
        StringBuilder sb = new StringBuilder();
        int numberOfEmptySquares = 0;
        for (int rank = 0; rank < Board.NUMBER_OF_RANKS; rank++) {
            for (int file = 0; file < Board.NUMBER_OF_FILES; file++) {
                int piece = board.getPieceAt(file, rank);
                if (piece == Piece.EMPTY) {
                    numberOfEmptySquares++;
                } else {
                    if (numberOfEmptySquares > 0) {
                        sb.append(numberOfEmptySquares);
                        numberOfEmptySquares = 0;
                    }
                    sb.append(PieceUtils.toChar(piece));
                }
            }
            if (numberOfEmptySquares > 0) {
                sb.append(numberOfEmptySquares);
                numberOfEmptySquares = 0;
            }
            if (rank < Board.NUMBER_OF_RANKS - 1) {
                sb.append("/");
            }
        }
        sb.append(" ");
        sb.append(activeColor == Color.WHITE ? "w" : "b");
        sb.append(" ");
        if (!(castlingAvailability[0] || castlingAvailability[1] || castlingAvailability[2]
                || castlingAvailability[3])) {
            sb.append("-");
        } else {
            sb.append(castlingAvailability[0] ? "K" : "");
            sb.append(castlingAvailability[1] ? "Q" : "");
            sb.append(castlingAvailability[2] ? "k" : "");
            sb.append(castlingAvailability[3] ? "q" : "");
        }
        sb.append(" ");
        if (enpassantFile == -1) {
            sb.append("-");
        } else {
            sb.append((char) ('a' + enpassantFile));
            sb.append((char) ('1' + enpassantRank));
        }
        sb.append(" ");
        sb.append(halfmoveClock);
        sb.append(" ");
        sb.append(fullmoveNumber);
        return sb.toString();
    }

    /**
     * Checks if valid fen by using regualar expression.
     */
    public void validateFenSyntax(String fen) {
        if (!fen.matches(
                "\s*([rnbqkpRNBQKP1-8]+/){7}([rnbqkpRNBQKP1-8]+)\s[bw-]\s(([a-hkqA-HKQ]{1,4})|(-))\s(([a-h][36])|(-))\s[0-9]+\s[0-9]+\s*")) {
            throw new IllegalArgumentException("Invalid FEN syntax: " + fen);
        }
    }
}

// /**
// * Checks if given fen is valid by using regular expression.
// *
// * @param fen
// * @return
// */
// private boolean isValidFen(String fen) {
// return fen
// .matches("^([KQRBNP]{1,8}/){7}[KQRBNP]{1,8}\\s[wb]\\s[KQRBNP]{1,8}\\s[wb]\\s[0-9]{1,4}\\s[0-9]{1,4}$");
// }