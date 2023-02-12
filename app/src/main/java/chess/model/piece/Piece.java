package chess.model.piece;

public abstract class Piece {

    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean isWhite() {
        return Color.WHITE.equals(color);
    }

    public boolean isBlack() {
        return Color.BLACK.equals(color);
    }

}