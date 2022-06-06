package chess.model.piece;

import chess.model.piece.properties.Color;

/**
 * PieceUtils
 */
public class PieceUtils {

    public static char toChar(int piece) {
        int type = Piece.getType(piece);
        int color = Piece.getColor(piece);
        return switch (type) {
            case Piece.EMPTY -> '-';
            case Piece.PAWN -> toUpperOrLowerCase(color, 'P');
            case Piece.ROOK -> toUpperOrLowerCase(color, 'R');
            case Piece.BISHOP -> toUpperOrLowerCase(color, 'B');
            case Piece.KNIGHT -> toUpperOrLowerCase(color, 'N');
            case Piece.QUEEN -> toUpperOrLowerCase(color, 'Q');
            case Piece.KING -> toUpperOrLowerCase(color, 'K');
            default -> throw new IllegalArgumentException("Invalid piece type: " + type);
        };
    }

    public static int fromChar(char ch) {
        int type = switch (Character.toUpperCase(ch)) {
            case 'P'-> Piece.PAWN;
            case 'R'-> Piece.ROOK;
            case 'B'-> Piece.BISHOP;
            case 'N'-> Piece.KNIGHT;
            case 'Q'-> Piece.QUEEN;
            case 'K'-> Piece.KING;
            default -> throw new IllegalArgumentException("Invalid piece type: " + ch);
        };
        return Character.isUpperCase(ch) ? Piece.create(type, Color.WHITE) : Piece.create(type, Color.BLACK);
    }
    
    private static char toUpperOrLowerCase(int color, char ch) {
        return color == Color.WHITE ? ch : Character.toLowerCase(ch);
    }

}