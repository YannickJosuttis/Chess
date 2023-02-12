package chess.model.board;

public class CastlingAvailability {

    private static final int WHITEKINGSIDE = 0, WHITEQUEENSIDE = 1, BLACKKINGSIDE = 2, BLACKQUEENSIDE = 3;
    private boolean[] castlingAvailability;

    public CastlingAvailability() {
        castlingAvailability = new boolean[4];
    }

    public boolean isWhiteKingsideAvailable() {
        return castlingAvailability[WHITEKINGSIDE];
    }

    public boolean isWhiteQueensideAvailable() {
        return castlingAvailability[WHITEQUEENSIDE];
    }

    public boolean isBlackKingsideAvailable() {
        return castlingAvailability[BLACKKINGSIDE];
    }

    public boolean isBlackQueensideAvailable() {
        return castlingAvailability[BLACKQUEENSIDE];
    }

    public void setWhiteKingsideAvailability(boolean isAvailable) {
        castlingAvailability[WHITEKINGSIDE] = isAvailable;
    }

    public void setWhiteQueensideAvailability(boolean isAvailable) {
        castlingAvailability[WHITEQUEENSIDE] = isAvailable;
    }

    public void setBlackKingsideAvailability(boolean isAvailable) {
        castlingAvailability[BLACKKINGSIDE] = isAvailable;
    }

    public void setBlackQueensideAvailability(boolean isAvailable) {
        castlingAvailability[BLACKQUEENSIDE] = isAvailable;
    }

}
