package chess.model.move;

import chess.model.Square;
import chess.model.piece.IPiece;

public class Promotion extends Move implements IPromotion {

    private IPiece promotionPiece;

    
    public Promotion(Square source, Square destination, IPiece promotionPiece) {
        super(source, destination);
        this.promotionPiece = promotionPiece;
    }

    @Override
    public IPiece getPromotionPiece() {
        return promotionPiece;
    }
    
}
