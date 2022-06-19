package chess.model.move;

import chess.model.Square;
import chess.model.piece.IPiece;

public class EnPassant extends Capture {

    
    public EnPassant(Square source, Square destination, IPiece captured) {
        super(source, destination, captured);
    }
    
}
