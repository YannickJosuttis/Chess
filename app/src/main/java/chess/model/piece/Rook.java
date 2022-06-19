package chess.model.piece;

import java.util.List;

import chess.model.Position;
import chess.model.move.Move;
import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public List<Move> getPseudoLegalMoves(Position position) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
