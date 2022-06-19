package chess.model.piece;

import java.util.List;

import chess.model.Position;
import chess.model.move.Move;
import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public List<Move> getPseudoLegalMoves(Position position) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
