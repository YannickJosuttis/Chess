package chess.model.piece;

import java.util.List;

import chess.model.move.Move;
import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

public class King extends Piece {

    public King(Color color) {
        super(color, Type.KING);
    }

    @Override
    public List<Move> getMoves() {
        return null;
    }
    
}
