package chess.model.piece.utils;

import chess.model.piece.Bishop;
import chess.model.piece.IPiece;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

/**
 * PieceUtils
 */
public class PieceUtils {

    public static char toChar(IPiece piece) {
        return piece.getColor() == Color.WHITE ? Character.toUpperCase(toChar(piece.getType()))
                : Character.toLowerCase(toChar(piece.getType()));
    }

    private static char toChar(Type type) {
        return switch (type) {
            case PAWN -> 'p';
            case ROOK -> 'r';
            case KNIGHT -> 'n';
            case BISHOP -> 'b';
            case QUEEN -> 'q';
            case KING -> 'k';
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    public static IPiece fromChar(char c) {
        Color color = Character.isUpperCase(c) ? Color.WHITE : Color.BLACK;
        return switch (Character.toUpperCase(c)) {
            case 'P' -> new Pawn(color);
            case 'R' -> new Rook(color);
            case 'N' -> new Knight(color);
            case 'B' -> new Bishop(color);
            case 'Q' -> new Queen(color);
            case 'K' -> new King(color);
            default -> throw new IllegalArgumentException("Unknown char: " + c);
        };
    }

}