package chess.model.move;

import chess.model.board.Square;
import chess.model.piece.Piece;

public class Promotion extends DefaultMove {

    protected Piece promotionPiece;

    public Promotion(Square source, Square destination, Piece promotionPiece) {
        super(source, destination);
        this.promotionPiece = promotionPiece;
    }

    @Override
    public void execute() {
        super.execute();
        getDestination().setPiece(promotionPiece);
    }

    @Override
    public void undo() {
        super.undo();
    }

}
