package chess.model.piece;

public class Piece {

    public static final int EMPTY = 0b0000;
    public static final int PAWN = 0b0001;
    public static final int ROOK = 0b0010;
    public static final int BISHOP = 0b0011;
    public static final int KNIGHT = 0b0100;
    public static final int QUEEN = 0b0101;
    public static final int KING = 0b0110;

    private static final int COLOR_MASK = 0b11000;
    private static final int TYPE_MASK = 0b00111;

    public static int getColor(int piece) {
        return piece & COLOR_MASK;
    }

    public static int getType(int piece) {
        return piece & TYPE_MASK;
    }

    public static int create(int color, int type) {
        return color | type;
    }

}
