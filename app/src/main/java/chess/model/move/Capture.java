package chess.model.move;

import chess.model.Square;
import chess.model.piece.IPiece;

/**
 * Capture
 */
public class Capture extends Move implements ICapture {

    private IPiece capturedPiece;

	public Capture(Square source, Square destination, IPiece captured) {
		super(source, destination);
        this.capturedPiece = captured;
	}

    public IPiece getCapturedPiece() {
        return capturedPiece;
    }

}