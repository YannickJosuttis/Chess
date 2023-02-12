package chess.model.move;

import chess.model.board.Square;
import chess.model.piece.Piece;

public class Capture extends Move {

    protected Piece capturedPiece;

    public Capture(Square source, Square destination, Piece capturedPiece) {
        super(source, destination);
        this.capturedPiece = capturedPiece;
    }

    @Override
    public void execute() {
        if (source.isEmpty()) {
            throw new IllegalStateException("Source must not be empty!");
        }
        if (destination.isEmpty()) {
            throw new IllegalStateException("Destination must not be empty!");
        }
        destination.setPiece(source.getPiece());
        source.removePiece();
    }

    @Override
    public void undo() {
        source.setPiece(destination.getPiece());
        destination.setPiece(capturedPiece);
    }
    
}
