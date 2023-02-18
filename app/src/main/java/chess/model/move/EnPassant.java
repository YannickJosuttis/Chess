package chess.model.move;

import chess.model.board.Square;
import chess.model.piece.Piece;

public class EnPassant extends DefaultMove {

    private Square enPassantTargetSquare;

    private Piece capturedPiece;

    public EnPassant(Square source, Square destination, Square enPassantTargetSquare) {
        super(source, destination);
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.capturedPiece = enPassantTargetSquare.getPiece();
    }

    @Override
    public void execute() {
        super.execute();
        enPassantTargetSquare.removePiece();
    }

    @Override
    public void undo() {
        super.undo();
        enPassantTargetSquare.setPiece(capturedPiece);
    }
    
}
