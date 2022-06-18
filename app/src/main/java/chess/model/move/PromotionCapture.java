package chess.model.move;

import chess.model.Square;
import chess.model.piece.IPiece;

public class PromotionCapture extends Move implements ICapture, IPromotion {

    private IPiece promotionPiece;
    private IPiece capturedPiece;

    public PromotionCapture(Square source, Square destination, IPiece promotionPiece, IPiece capturedPiece) {
        super(source, destination);
    }

    @Override
    public IPiece getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public IPiece getCapturedPiece() {
        return capturedPiece;
    }
    
}
