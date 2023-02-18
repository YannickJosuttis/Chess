package chess.model.move;

import chess.model.board.Square;
import chess.model.piece.Piece;

public class Capture extends DefaultMove {
    
    private Square captureSquare;
    private Piece capturedPiece;

    public Capture(Square source, Square destination, Square captureSquare) {
        super(source, destination);
        this.captureSquare = captureSquare;
        this.capturedPiece = captureSquare.getPiece();
    }

    @Override
    public void execute() {
        super.execute();
        captureSquare.removePiece();
    }

    @Override
    public void undo() {
        super.undo();
        captureSquare.setPiece(capturedPiece);
    }

}
