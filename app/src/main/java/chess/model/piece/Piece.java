package chess.model.piece;

import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

public abstract class Piece implements IPiece {

    private Color color;
    private Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Type getType() {
        return type;
    }
    
}
